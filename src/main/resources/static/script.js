
const API_URL = "/api/students";

let editingStudentId = null;


// 1. Load dashboard statistics
async function loadDashboard() {
    try {
        const response = await fetch(`${API_URL}/dashboard`);

        if (!response.ok) {
            throw new Error("Failed to load dashboard");
        }

        const data = await response.json();

        document.getElementById("totalStudents").textContent = data.totalStudents;
        document.getElementById("activeStudents").textContent = data.activeStudents;
        document.getElementById("inactiveStudents").textContent = data.inactiveStudents;

    } catch (error) {
        console.error(error);
    }
}


// 2. Load students
async function loadStudents() {

    const search = document.getElementById("search").value;
    const course = document.getElementById("courseFilter").value;
    const status = document.getElementById("statusFilter").value;

    const params = new URLSearchParams();

    if (search) params.append("search", search);
    if (course) params.append("course", course);
    if (status) params.append("status", status);

    try {
        const response = await fetch(`${API_URL}?${params.toString()}`);

        if (!response.ok) {
            throw new Error("Failed to load students");
        }

        const students = await response.json();

        displayStudents(students);

    } catch (error) {
        document.getElementById("message").textContent =
            "Unable to load students.";

        console.error(error);
    }
}


// 3. Display students in table
function displayStudents(students) {

    const tableBody = document.getElementById("studentTableBody");

    tableBody.innerHTML = "";

    if (students.length === 0) {
        tableBody.innerHTML = `
            <tr>
                <td colspan="8">No students found.</td>
            </tr>
        `;
        return;
    }

    students.forEach(student => {

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.fullName}</td>
            <td>${student.email}</td>
            <td>${student.phoneNumber}</td>
            <td>${student.course}</td>
            <td>${student.enrollmentDate}</td>
            <td>${student.status}</td>
            <td>
                <button class="action-button"
                    onclick="editStudent(${student.id})">
                    Edit
                </button>

                <button class="action-button delete-button"
                    onclick="deleteStudent(${student.id})">
                    Delete
                </button>
            </td>
        `;

        tableBody.appendChild(row);
    });
}


// 4. Add or update student
document.getElementById("studentForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const student = {
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        course: document.getElementById("course").value,
        enrollmentDate: document.getElementById("enrollmentDate").value,
        status: document.getElementById("status").value
    };

    try {

        const response = await fetch(
            editingStudentId ? `${API_URL}/${editingStudentId}` : API_URL,
            {
                method: editingStudentId ? "PUT" : "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(student)
            }
        );

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.error || "Operation failed");
        }

        document.getElementById("message").textContent =
            editingStudentId
                ? "Student updated successfully!"
                : "Student added successfully!";

        editingStudentId = null;

        document.getElementById("studentForm").reset();

        document.getElementById("formTitle").textContent = "Add New Student";
        document.getElementById("submitButton").textContent = "Add Student";
        document.getElementById("cancelButton").hidden = true;

        loadStudents();
        loadDashboard();

    } catch (error) {
        document.getElementById("message").textContent = error.message;
    }
});


// 5. Edit student
async function editStudent(id) {

    try {

        const response = await fetch(`${API_URL}/${id}`);

        if (!response.ok) {
            throw new Error("Unable to fetch student details");
        }

        const student = await response.json();

        editingStudentId = id;

        document.getElementById("fullName").value = student.fullName;
        document.getElementById("email").value = student.email;
        document.getElementById("phoneNumber").value = student.phoneNumber;
        document.getElementById("course").value = student.course;
        document.getElementById("enrollmentDate").value = student.enrollmentDate;
        document.getElementById("status").value = student.status;

        document.getElementById("formTitle").textContent = "Edit Student";
        document.getElementById("submitButton").textContent = "Update Student";
        document.getElementById("cancelButton").hidden = false;

        document.getElementById("studentForm").scrollIntoView({
            behavior: "smooth"
        });

    } catch (error) {
        document.getElementById("message").textContent = error.message;
    }
}


// 6. Cancel edit
document.getElementById("cancelButton").addEventListener("click", function() {

    editingStudentId = null;

    document.getElementById("studentForm").reset();

    document.getElementById("formTitle").textContent = "Add New Student";
    document.getElementById("submitButton").textContent = "Add Student";
    document.getElementById("cancelButton").hidden = true;
});


// 7. Search and filter events
document.getElementById("search").addEventListener("input", loadStudents);

document.getElementById("courseFilter").addEventListener("input", loadStudents);

document.getElementById("statusFilter").addEventListener("change", loadStudents);


// 8. Initial loading
loadDashboard();
loadStudents();


async function deleteStudent(id) {

    const confirmDelete = confirm(
        "Are you sure you want to delete this student?"
    );

    if (!confirmDelete) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Unable to delete student");
        }

        // If the deleted student is currently being edited,
        // reset the form.
        if (editingStudentId === id) {
            editingStudentId = null;

            document.getElementById("studentForm").reset();
            document.getElementById("formTitle").textContent = "Add New Student";
            document.getElementById("submitButton").textContent = "Add Student";
            document.getElementById("cancelButton").hidden = true;
        }

        document.getElementById("message").textContent =
            "Student deleted successfully!";

        loadStudents();
        loadDashboard();

    } catch (error) {
        document.getElementById("message").textContent = error.message;
    }
}
