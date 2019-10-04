'use strict';

const BASE_URL = "/LamarBorkoProject/api";
const EMPLOYEE_URL = `${BASE_URL}/employees`;

let createEmployee = document.getElementById("create_employee");

createEmployee.addEventListener("submit", (event)=>{
  event.preventDefault();
  console.log(employeeFromForm(createEmployee));
  fetch(EMPLOYEE_URL,
      { method: "POST", body: JSON.stringify(employeeFromForm(createEmployee)) }
      )
      .then((response)=> {
        console.log(response);
        if(response.status >= 200 && response.status < 300) {
          alert("Employee created");
        } else {
          alert("Failed to create employee");
        }
      })
      .catch(console.error);
});
