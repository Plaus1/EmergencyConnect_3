const menuToggle = document.getElementById('menuToggle');
const nav = document.getElementById('nav');
menuToggle.addEventListener('click', () => nav.classList.toggle('open'));
nav.querySelectorAll('a').forEach(link => link.addEventListener('click', () => nav.classList.remove('open')));
document.getElementById('year').textContent = new Date().getFullYear();

async function submitForm(form, endpoint, messageElement, successFallback) {
  const button = form.querySelector('button[type="submit"]');
  const message = document.getElementById(messageElement);
  const data = Object.fromEntries(new FormData(form).entries());
  message.textContent = 'Submitting…';
  message.className = 'form-message';
  button.disabled = true;
  try {
    const response = await fetch(endpoint, {
      method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(data)
    });
    const result = await response.json();
    if (!response.ok) throw new Error(result.message || 'The request could not be submitted.');
    message.textContent = result.message || successFallback;
    message.classList.add('success');
    form.reset();
  } catch (error) {
    message.textContent = `Could not connect to the server. Start the Kotlin app and try again. (${error.message})`;
    message.classList.add('error');
  } finally { button.disabled = false; }
}

document.getElementById('reportForm').addEventListener('submit', event => {
  event.preventDefault();
  submitForm(event.currentTarget, '/api/reports', 'reportMessage', 'Report submitted.');
});
document.getElementById('volunteerForm').addEventListener('submit', event => {
  event.preventDefault();
  submitForm(event.currentTarget, '/api/volunteers', 'volunteerMessage', 'Thank you for offering to help.');
});
