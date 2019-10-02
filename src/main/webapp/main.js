'use strict';

const BASE_URL = "/project-1-lamarandborko/api";
const REIMBURSEMENT_URL = `${BASE_URL}/reimbursements`;

let singleReimbursementGet = document.getElementById("single-reimbursement-get");
let createReimbursement = document.getElementById("create-reimbursement");
let updateReimbursement = document.getElementById("update-reimbursement");
let reimbursementDisplay = document.getElementById("reimbursement-display");
let getAllReimbursements = document.getElementById("get-all-reimbursements");

getAllReimbursements.addEventListener("click", (event)=>{
  fetch(REIMBURSEMENT_URL, { method: "GET" })
      .then((response)=>{
        return response.json();
      })
      .then((reimbursementsJson)=>{
        clearDisplay();
        for(let reimbursement in reimbursementsJson) {
          console.log(reimbursementsJson[reimbursement]);
          createLi(reimbursementsJson[reimbursement]);
        }
      })
      .catch(console.log);
});

singleReimbursementGet.addEventListener("submit", (event)=>{
	  event.preventDefault();

	  let reimbursementId = singleReimbursementGet.reimbursementId.value;
	  fetch(`${REIMBURSEMENT_URL}/${reimbursementId}`)
	      .then((response)=>{
	        return response.json();
	      })
	      .then((reimbursementJson)=>{
	        clearDisplay();
	        createLi(reimbursementJson);
	      })
	      .catch((error)=> {
	        console.error(error);
	        alert(`Failed to find Reimbursement : ${reimbursementId}`);
	      });
	});

	createReimbursement.addEventListener("submit", (event)=>{
	  event.preventDefault();
	  console.log(reimbursementFromForm(createReimbursement));
	  fetch(REIMBURSEMENT_URL,
	      { method: "POST", body: JSON.stringify(reimbursementFromForm(createReimbursement)) }
	      )
	      .then((response)=> {
	        console.log(response);
	        if(response.status >= 200 && response.status < 300) {
	          alert("Reimbursement created");
	        } else {
	          alert("Failed to create Reimbursement");
	        }
	      })
	      .catch(console.error);
	});

	updateReimbursement.addEventListener("submit", (event) => {
	  event.preventDefault();
	  fetch(REIMBURSEMENT_URL,
	    { method: "PUT", body: JSON.stringify(reimbursementFromForm(updateReimbursement))}
	    )
	    .then((response) => {
	      if(response.status >= 200 && response.status < 300) {
	        alert("Reimbursement updated");
	      } else {
	        alert("Failed to update reimbursement");
	      }
	      updateReimbursement.hidden = true;
	    })
	})

	let reimbursementFromForm = (form) => {
	  let reimbursement = {};
	  reimbursement.id = form.id.value || 0;
	  reimbursement.employee_id = form.employeeid.value || 0;
	  reimbursement.title = form.title.value || 'title';
	  reimbursement.amountrequested = form.amountrequested.value || 0;
	  reimbursement.daterequested = form.daterequested.value || 'mm/dd/yy';
	  reimbursement.status = form.status.value || 'status';
	  reimbursement.resolvedstatus = form.resolvedstatus.value || 'resolved status';
	  reimbursement.picture = form.picture.value || 'picture';
	  return Reimbursement;
	}

	let clearDisplay = () => {
	  reimbursementDisplay.innerHTML = "";
	}

	let createLi = (reimbursement) => {
	  let li = document.createElement("li");
	  li.innerText = `${reimbursement.title} : Number ${reimbursement.amountrequested}, ${reimbursement.picture}`;
	  li.addEventListener("click", () => {
	    updateReimbursement.id.value = reimbursement.id;
	    updateReimbursement.title.value = reimbursement.title;
	    updateReimbursement.amountrequested.value = reimbursement.amountrequested;
	    updateReimbursement.picture.value = reimbursement.picture;
	    updateReimbursement.hidden = false;
	    clearDisplay();
	  });
	  reimbursementDisplay.append(li);
	}