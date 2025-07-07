const apiUrl = "/api/attendance";

// ✅ Submit attendance
document.getElementById("attendanceForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const data = {
    studentName: document.getElementById("studentName").value,
    date: document.getElementById("date").value,
    present: document.getElementById("present").checked
  };

  await fetch(apiUrl, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });

  document.getElementById("attendanceForm").reset();
  loadAttendance();
});

// ✅ Load all attendance
async function loadAttendance() {
  const res = await fetch(apiUrl);
  const records = await res.json();
  populateTable(records);
}

// ✅ Search attendance
async function searchAttendance() {
  const name = document.getElementById("searchName").value;
  const date = document.getElementById("searchDate").value;

  let url = "/api/attendance/search?";
  if (name) url += `studentName=${encodeURIComponent(name)}&`;
  if (date) url += `date=${date}`;

  const res = await fetch(url);
  const data = await res.json();
  populateTable(data);
}

// ✅ Reusable table rendering
function populateTable(records) {
  const tableBody = document.querySelector("#attendanceTable tbody");
  tableBody.innerHTML = "";

  records.forEach((item) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${item.id}</td>
      <td>${item.studentName}</td>
      <td>${item.date}</td>
      <td>${item.present ? "✅" : "❌"}</td>
      <td><button onclick="deleteRecord(${item.id})">Delete</button></td>
    `;
    tableBody.appendChild(row);
  });
}

// ✅ Delete a record
async function deleteRecord(id) {
  await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
  loadAttendance();
}

// ✅ Initial load
loadAttendance();
async function loadReport() {
  const res = await fetch("/api/attendance/report");
  const data = await res.json();
  const tableBody = document.querySelector("#reportTable tbody");
  tableBody.innerHTML = "";

  data.forEach(item => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${item.studentName}</td>
      <td>${item.total}</td>
      <td>${item.present}</td>
      <td>${item.absent}</td>
      <td>${item.percentage.toFixed(2)}%</td>
    `;
    tableBody.appendChild(row);
  });
}
function addStudent(e) {
  e.preventDefault();
  const name = document.getElementById('studentName').value;
  const roll = document.getElementById('rollNo').value;

  fetch('/api/students', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name, rollNumber: roll })
  }).then(() => {
    document.getElementById('studentName').value = "";
    document.getElementById('rollNo').value = "";
    loadStudents();
  });
}

function loadStudents() {
  fetch('/api/students')
    .then(res => res.json())
    .then(data => {
      const rows = data.map(s => `
        <tr>
          <td>${s.id}</td>
          <td>${s.name}</td>
          <td>${s.rollNumber}</td>
          <td><button onclick="deleteStudent(${s.id})">❌</button></td>
        </tr>
      `).join('');
      document.querySelector('#studentTable tbody').innerHTML = rows;
    });
}

function deleteStudent(id) {
  fetch(`/api/students/${id}`, { method: 'DELETE' }).then(loadStudents);
}

