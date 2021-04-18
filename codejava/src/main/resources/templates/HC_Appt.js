/* Setup the calendar with the current date */
$(document).ready(function () {
    refreshData()
    var date = new Date();
    var today = date.getDate();
    // Set click handlers for DOM elements
    $(".right-button").click({date: date}, next_year);
    $(".left-button").click({date: date}, prev_year);
    $(".month").click({date: date}, month_click);
    $("#add-button").click({date: date}, new_event);
    $("#update-button").click({date: date}, update_event);

    //rmz: Added the delete button click event
    $("#delete-button").click({date: date}, delete_event);
    // Set current month as active
    $(".months-row").children().eq(date.getMonth()).addClass("active-month");
    init_calendar(date);
    var events = check_events(today, date.getMonth() + 1, date.getFullYear());

    show_events(events, months[date.getMonth()], today);
});

// Initialize the calendar by appending the HTML dates
function init_calendar(date) {

    $(".tbody").empty();
    $(".events-container").empty();
    var calendar_days = $(".tbody");
    var month = date.getMonth();
    var year = date.getFullYear();
    var day_count = days_in_month(month, year);
    var row = $("<tr class='table-row'></tr>");
    var today = date.getDate();
    // Set date to 1 to find the first day of the month
    date.setDate(1);
    var first_day = date.getDay();
    // 35+firstDay is the number of date elements to be added to the dates table
    // 35 is from (7 days in a week) * (up to 5 rows of dates in a month)
    for (var i = 0; i < 35 + first_day; i++) {
        // Since some of the elements will be blank,
        // need to calculate actual date from index
        var day = i - first_day + 1;
        // If it is a sunday, make a new row
        if (i % 7 === 0) {
            calendar_days.append(row);
            row = $("<tr class='table-row'></tr>");
        }
        // if current index isn't a day in this month, make it blank
        if (i < first_day || day > day_count) {
            var curr_date = $("<td class='table-date nil'>" + "</td>");
            row.append(curr_date);
        } else {
            var curr_date = $("<td class='table-date'>" + day + "</td>");
            var events = check_events(day, month + 1, year);

            if (today === day && $(".active-date").length === 0) {
                curr_date.addClass("active-date");
                show_events(events, months[month], day);
            }
            // If this date has any events, style it with .event-date
            if (events.length !== 0) {
                curr_date.addClass("event-date");
            }
            // Set onClick handler for clicking a date
            curr_date.click({
                events: events,
                month: months[month],
                day: day
            }, date_click);
            row.append(curr_date);
        }
    }
    // Append the last row and set the current year
    calendar_days.append(row);
    $(".year").text(year);
}

// Get the number of days in a given month/year
function days_in_month(month, year) {
    var monthStart = new Date(year, month, 1);
    var monthEnd = new Date(year, month + 1, 1);
    return (monthEnd - monthStart) / (1000 * 60 * 60 * 24);
}

// Event handler for when a date is clicked
function date_click(event) {
    $(".events-container").show(250);
    $("#dialog").hide(250);
    $("#deldialog").hide(250);
    $(".active-date").removeClass("active-date");
    $(this).addClass("active-date");
    show_events(event.data.events, event.data.month, event.data.day);
};

// Event handler for when a month is clicked
function month_click(event) {
    $(".events-container").show(250);
    $("#dialog").hide(250);
    $("#deldialog").hide(250);
    var date = event.data.date;
    $(".active-month").removeClass("active-month");
    $(this).addClass("active-month");
    var new_month = $(".month").index(this);
    date.setMonth(new_month);
    init_calendar(date);
}

// Event handler for when the year right-button is clicked
function next_year(event) {
    $("#dialog").hide(250);
    $("#deldialog").hide(250);
    var date = event.data.date;
    var new_year = date.getFullYear() + 1;
    $("year").html(new_year);
    date.setFullYear(new_year);
    init_calendar(date);
}

// Event handler for when the year left-button is clicked
function prev_year(event) {
    $("#dialog").hide(250);
    $("#deldialog").hide(250);
    var date = event.data.date;
    var new_year = date.getFullYear() - 1;
    $("year").html(new_year);
    date.setFullYear(new_year);
    init_calendar(date);
}

function update_event(event) {
    console.info('update')
    const id = $("input[type=radio]:checked").attr('data-id')
    if (!id) return
    const {events = []} = event_data
    const appointment = events.find(d => d.appointmentId === parseInt(id))
    if (!appointment) return;

    $("#deldialog").hide(250);
    // if a date isn't selected then do nothing
    if ($(".active-date").length === 0)
        return;

    // remove red error input on click
    $("input").click(function () {
        $(this).removeClass("error-input");
    })


    const {invited_count, type, time} = appointment
    $('#count').val(invited_count)


    $('#updateDlgHd').text('Update Appointment');
    $("#dialog").show(250);
    $(`#type option[value=${type}]`).attr('selected', true)
    $(`#time option[value='${time}']`).attr('selected', true)
    // Event handler for cancel button
    $("#cancel-button").click(function () {
        //$("#name").removeClass("error-input");
        $("#count").removeClass("error-input");
        $("#dialog").hide(250);
        $("#deldialog").hide(250);
        $(".events-container").show(250);
    });

    // Event handler for ok button
    $("#ok-button").unbind().click({date: event.data.date}, function () {
        var date = event.data.date;
        //var name = $("#name").val().trim();
        var time = $("#time").val().trim();
        var invitedCount = parseInt($("#count").val().trim());

        var day = parseInt($(".active-date").html());
        const choiceDate = `${date.getFullYear()}-${date.getMonth() + 1}-${day}`;

        if (checkDate(choiceDate, time)) {
            return;
        }

        if (isNaN(invitedCount) || invitedCount < 1) {
            $("#count").addClass("error-input");
            alert('Number of slots must be positive integer')
        } else {
            $("#dialog").hide(250);
            $("#deldialog").hide(250);


            const data = {
                day,
                invitedCount,
                time,
                year: date.getFullYear(),
                month: date.getMonth() + 1,
                type: $('#type').val()
            }
            data.centerId = $('#centerId').val();
            data.appointmentId = id
            doPost(['/hc/appointments', JSON.stringify(data)], (rlt) => {
                console.info('rlt', rlt)
                refreshData()
                date.setDate(day);
                init_calendar(date);
            })
        }
    });
}

const dateSource = {
    '9:00 AM - 10:00 AM': '10:00',
    '10:00AM-11:00AM': '11:00',
    '11:00AM-12:00PM': '12:00',
    '12:00PM-1:00PM': '13:00',
    '1:00PM-2:00PM': '14:00',
    '2:00PM-3:00PM': '15:00',
    '3:00PM-4:00PM': '16:00',
}
const checkDate = (date, time) => {

    const dateNow = new Date()

    const choiceDate = new Date(`${date} ${dateSource[time]}`)

    console.info('choiceDate dateNow', choiceDate, dateNow)

    const disable = choiceDate < dateNow;

    if (disable) {
        alert('please select a future date')
    }
    return disable
}

// Event handler for clicking the new event button
function new_event(event) {
    //if delete appointment dialog is open , then close it
    $("#deldialog").hide(250);
    // if a date isn't selected then do nothing
    if ($(".active-date").length === 0)
        return;
    // remove red error input on click
    $("input").click(function () {
        $(this).removeClass("error-input");
    })


    // empty inputs and hide events
    $("#dialog input[type=text]").val('');
    $("#dialog input[type=number]").val('');
    $(".events-container").hide(250);

    $('#updateDlgHd').text('Add New Appointment');
    $("#dialog").show(250);
    // Event handler for cancel button
    $("#cancel-button").click(function () {
        //$("#name").removeClass("error-input");
        $("#count").removeClass("error-input");
        $("#dialog").hide(250);
        $("#deldialog").hide(250);
        $(".events-container").show(250);
    });
    // Event handler for ok button
    $("#ok-button").unbind().click({date: event.data.date}, function () {
        var date = event.data.date;
        //var name = $("#name").val().trim();
        var time = $("#time").val().trim();
        var invitedCount = parseInt($("#count").val().trim());

        var day = parseInt($(".active-date").html());
        const choiceDate = `${date.getFullYear()}-${date.getMonth() + 1}-${day}`;

        if (checkDate(choiceDate, time)) {
            return;
        }

        if (isNaN(invitedCount) || invitedCount < 1) {
            $("#count").addClass("error-input");
            alert('Number of slots must be positive integer')
        }
        if (isNaN(invitedCount) || invitedCount > 20) {
            $("#count").addClass("error-input");
            alert('The range of slots must be 0~20')
        }
        else {
            $("#dialog").hide(250);
            $("#deldialog").hide(250);


            const data = {
                day,
                invitedCount,
                time,
                year: date.getFullYear(),
                month: date.getMonth() + 1,
                type: $('#type').val()
            }
            data.centerId = $('#centerId').val();
            doPost(['/hc/appointments', JSON.stringify(data)], (rlt) => {
                console.info('rlt', rlt)
                refreshData()
                date.setDate(day);
                init_calendar(date);
            })
        }
    });
}

//////////////////////////////////////////////////////////
//rmz: Added the delete button click event function
// Event handler for clicking the delete event button
function delete_event(event) {

    const today = new Date();
    const today_date = today.getDate();
    const x = document.querySelector(".active-date").innerText;

    if (x <= today_date) {
        $("#deldialog").hide(250)
        alert("Please select a future date");
        $("#deldialog").hide(250)
    } else {


        const id = $(".events-container input[type=radio]:checked").attr('data-id')


        $(".events-container").hide(250);
        $("#dialog").hide(250);
        $("#deldialog").show(250);
        let $event = $("#delform .event-card");
        $event.show(250);


        if (!id) {
            $event.html("Please select delete item!")
        } else {
            $event.html("Confirm deletion this item ?")
        }
        //if there is any appointmnet for the selected date, display it:

        //rmz
        // Event handler for clicking the Cancel delete button
        $("#cancel-delbutton").click(function () {
            $("#count").removeClass("error-input");
            $("#dialog").hide(250);
            $("#deldialog").hide(250);
            $(".events-container").show(250);
        });


        //Event handler for del-ok:
        $("#ok-delbutton").click(function (event) {
            let $checked = $(".events-container input[type=radio]:checked");
            const id = $checked.attr('data-id')
            if (id) {
                doDelete([`/hc/appointments/${id}`], () => {
                    console.info('delete success')

                })
                const {events = []} = event_data
                console.info('event', event)
                refreshData()
                const {
                    day,
                    month,
                    year
                } = events.find(({appointmentId}) => appointmentId == id)
                const date = new Date(`${year}-${month}-${day}`)
                console.info('date', date)
                init_calendar(date);
            }
            $("#dialog").hide(250);
            $("#deldialog").hide(250);
            $(".events-container").show(250);
        });
    }
}

///////////////////
// Display all events of the selected date in card views
function show_events(events, month, day) {
    // Clear the dates container
    $(".events-container").empty();
    $(".events-container").show(250);

    // If there are no events for this date, notify the user
    if (events.length === 0) {
        var event_card = $("<div class='event-card'></div>");
        var event_time = $("<div class='event-time'>There are no appointments scheduled for " + month + " " + day + ".</div>");
        $(event_card).append(event_time);
        $(".events-container").append(event_card);
    } else {
        // Go through and add each event as a card to the events container
        for (var i = 0; i < events.length; i++) {
            const event = events[i]
            event_card = $("<div class='event-card'></div>");


            event_time = $(`<div class='event-time hc-row'> <div class="hc-tag">${event.type}</div> ${event.time}:</div>`);

            var event_count = $("<div class='event-count'>" + events[i]["invited_count"] + ` Appointments <input data-day=${events[i]?.day} name='appo'  data-id=${events[i]?.appointmentId} type = 'radio' ></div>`);

            if (events[i]["cancelled"] === true) {
                $(event_card).css({
                    "border-left": "10px solid #FF1744"
                });
                event_time = $(`<div class='event-time'>${event.time}:</div>`)
                event_count = $("<div class='event-cancelled'>Cancelled</div>");
            }
            $(event_card).append(event_time).append(event_count);
            $(".events-container").append(event_card);
        }
    }
}

// Checks if a specific date has any events
function check_events(day, month, year) {
    var events = [];
    for (var i = 0; i < event_data["events"].length; i++) {
        var event = event_data["events"][i];
        if (event["day"] === day &&
            event["month"] === month &&
            event["year"] === year) {
            events.push(event);
        }
    }
    return events;
}

// Given data for events in JSON format
let event_data = {
    "events": [
        {
            "time": " 10:30 - 11:00 ",
            "invited_count": 120,
            "year": 2021,
            "month": 3,
            "day": 17,
            "cancelled": true
        },

        {
            "time": " Time Slot",
            "invited_count": 120,
            "year": 2017,
            "month": 5,
            "day": 11
        }
    ]
};

$("#delete-button").click(function () {
    const today = new Date();
    const today_date = today.getDate();
    const x = document.querySelector(".active-date").innerText;

    if (x <= today_date) {
        $("#deldialog").hide(250)
        alert("Please select a future date");
        $("#deldialog").hide(250)
    }
});


const refreshData = () => {
    const centerId = $('#centerId').val()
    doGet([`appointments/${centerId}`], (rlt) => {
        const events = rlt.map(({
                                    invitedCount: invited_count,
                                    ...o
                                }) => ({...o, invited_count}));

        event_data = {
            ...event_data,
            events
        }
    })
}


const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
];