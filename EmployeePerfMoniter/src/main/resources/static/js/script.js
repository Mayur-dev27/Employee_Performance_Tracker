// Function to display all employees in card view
async function showEmployees() {
    const response = await fetch('/api/employees/getEmployees');
    const employees = await response.json();
    const content = document.getElementById('content');

    content.innerHTML = `
        
        <div class="employee-cards">
            ${employees.map(emp => `
                <div class="employee-card" style="height: 15rem; width: 18rem">
                    <h2>Emp Id: ${emp.id}</h2>
                    <h3 class="employee-name">Name: ${emp.name} </br></br> Designation: (${emp.designation})</h3>
                    <div class="btns-container">
                        <button class="btn btn-view" onclick="viewEmployee(${emp.id})">View Details</button>
                        <button class="btn btn-delete" onclick="confirmDelete(${emp.id})">Delete</button>
                        <button class="btn btn-edit" onclick="editEmployee(${emp.id})">Edit</button>
                    </div>
                </div>
            `).join('')}
        </div>
    `;
}

// Function to display the Edit Employee form
async function editEmployee(employeeId) {
    const response = await fetch(`/api/employees/${employeeId}`);
    const employee = await response.json();
    const content = document.getElementById('content');

    content.innerHTML = `
        <div class="edit-employee-form">
            <button class="btn-back" onclick="showEmployees()">Back to Employees</button>
            <h2>Edit Employee</h2>
            <form id="editEmployeeForm">
                <input type="hidden" id="empId" value="${employee.id}" />
                <label for="empName">Name:</label>
                <input type="text" id="empName" name="name" value="${employee.name}" required /><br>
                <label for="empDesignation">Designation:</label>
                <input type="text" id="empDesignation" name="designation" value="${employee.designation}" required /><br>
                <button type="submit" class="btn-save">Save Changes</button>
            </form>
        </div>
    `;

    // Add event listener for form submission
    document.getElementById('editEmployeeForm').addEventListener('submit', async function (e) {
        e.preventDefault();

        const id = document.getElementById('empId').value;
        const name = document.getElementById('empName').value;
        const designation = document.getElementById('empDesignation').value;

        const updatedEmployee = { name, designation };

        try {
            const response = await fetch(`/api/employees/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedEmployee)
            });

            if (response.ok) {
                alert('Employee updated successfully!');
                showEmployees(); // Refresh the employee list after update
            } else {
                const errorData = await response.json();
                alert(`Failed to update employee: ${errorData.message || 'Unknown error'}`);
            }
        } catch (error) {
            alert(`Error: ${error.message}`);
        }
    });
}


// Function to confirm and delete an employee
async function confirmDelete(employeeId) {
    const confirmation = confirm("Are you sure you want to delete this employee?");
    
    if (confirmation) {
        // Call the backend to delete the employee
        try {
            const response = await fetch(`/api/employees/${employeeId}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                alert('Employee deleted successfully!');
                showEmployees(); // Refresh the employee list
            } else {
                const errorData = await response.json();
                alert(`Failed to delete employee: ${errorData.message || 'Unknown error'}`);
            }
        } catch (error) {
            alert(`Error: ${error.message}`);
        }
    } else {
        alert('Deletion canceled');
    }
}

// Function to display the Add Employee form
function showAddEmployeeForm() {
    const content = document.getElementById('content');
    
    content.innerHTML = `
        <div class="add-employee-form">
            <button class="btn-back" onclick="showEmployees()">Back to Employees</button>
            <h2>Add New Employee</h2>
            <form id="addEmployeeForm">
                <label for="empName">Name:</label>
                <input type="text" id="empName" name="name" required /><br>
                <label for="empDesignation">Designation:</label>
                <input type="text" id="empDesignation" name="designation" required /><br>
                <button type="submit" class="btn-save">Save Employee</button>
            </form>
        </div>
    `;

    // Add event listener for form submission
    document.getElementById('addEmployeeForm').addEventListener('submit', async function (e) {
        e.preventDefault();

        const name = document.getElementById('empName').value;
        const designation = document.getElementById('empDesignation').value;

        const employeeData = { name, designation };

        try {
            const response = await fetch('/api/employees/createEmp', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employeeData)
            });

            if (response.ok) {
                alert('Employee added successfully!');
                showEmployees(); // Refresh the employee list after adding
            } else {
                const errorData = await response.json();
                alert(`Failed to add employee: ${errorData.message || 'Unknown error'}`);
            }
        } catch (error) {
            alert(`Error: ${error.message}`);
        }
    });
}

// Function to display the details and reviews of a single employee
async function viewEmployee(employeeId) {
    const response = await fetch(`/api/employees/${employeeId}`);
    const employee = await response.json();
    const content = document.getElementById('content');

    content.innerHTML = `
        <div class="employee-detail">
            <button class="btn-back" onclick="showEmployees()">Back to Employees</button>
            <h2>Employee Details</h2>
            <p><strong>Name:</strong> ${employee.name}</p>
            <p><strong>Designation:</strong> ${employee.designation}</p>

            <h3>Performance Reviews</h3>
            ${employee.reviews.length > 0 ? `
                <ul class="review-list">
                    ${employee.reviews.map(review => `
                        <li class="review-item">
                            <strong>Feedback:</strong> ${review.feedback} <br>
                            <strong>Score:</strong> ${review.score} <br>
                            <strong>Review Date:</strong> ${review.reviewDate}
                        </li>
                    `).join('')}
                </ul>` : `<p>No reviews available for this employee.</p>`}

            <h3>Add a Review</h3>
            <form id="reviewForm">
                <label for="feedback">Feedback:</label>
                <textarea id="feedback" name="feedback" required></textarea><br>
                <label for="score">Score:</label>
                <input type="number" id="score" name="score" min="1" max="10" required /><br>
                <label for="reviewDate">Review Date:</label>
                <input type="date" id="reviewDate" name="reviewDate" required /><br>
                <button type="submit" class="btn-save">Save Review</button>
            </form>
        </div>
    `;

    document.getElementById('reviewForm').addEventListener('submit', async function (e) {
        e.preventDefault();

        const feedback = document.getElementById('feedback').value;
        const score = document.getElementById('score').value;
        const reviewDate = document.getElementById('reviewDate').value;

        const reviewData = { feedback, score, reviewDate };

        try {
            const response = await fetch(`/api/reviews/${employeeId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reviewData)
            });

            if (response.ok) {
                alert('Review added successfully!');
                viewEmployee(employeeId); // Refresh details
            } else {
                const errorData = await response.json();
                alert(`Failed to add review: ${errorData.message || 'Unknown error'}`);
            }
        } catch (error) {
            alert(`Error: ${error.message}`);
        }
    });
}

// Initialize page with employee list
document.addEventListener('DOMContentLoaded', showEmployees);
