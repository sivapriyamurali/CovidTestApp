/* Appointment.html - > Toggle Past and Upcmoming cards  */ 
var pastCard =document.getElementById("pastCard");
var btToggle = document.getElementById ("bttoggle");
var upcomingCard = document.getElementById("upcomingCard");

btToggle.addEventListener('click',myFunction);


function myFunction()
{
  pastCard.classList.toggle("cardtoggle");
  upcomingCard.classList.toggle("cardtoggle");
}