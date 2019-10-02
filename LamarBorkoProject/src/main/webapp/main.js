'use strict';

const BASE_URL = "/LamarBorkoProject/api";
const EMPLOYEE_URL = `${BASE_URL}/employees`;


let singleEmployeesGet = document.getElementById("get-single-employees");

singleEmployeesGet.addEventListener("submit", (event)=>{
	  event.preventDefault();

	  let playerId = singleEmployeesGet.playerId.value;
	  fetch(`${PLAYER_URL}/${playerId}`)
	      .then((response)=>{
	        return response.json();
	      })
	      .then((employeeJson)=>{
	        clearDisplay();
	        createLi(employeeJson);
	      })
	      .catch((error)=> {
	        console.error(error);
	        alert(`Failed to find Employee : ${employee-id}`);
	      });
	});