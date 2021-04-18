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
    return choiceDate < dateNow
}


$(function () {
    let uploaded = $('#uploadId').val();
    if (uploaded === 'true') {
        console.info('completed')
        $('#completed').attr('checked', true)
    } else if (checkDate($('#dateId').val(), $('#time').val())) {
        $('#pending').attr('checked', true)
    } else {
        $('#scheduled').attr('checked', true)
    }

    $(document).on('click', '#upload', () => {
        const file = $('#file')[0].files[0]
        if (!file) return

        const formData = new FormData()
        formData.append("file", file);
        formData.append("status", $("input[name=status]:checked").val());
        const id = $('#patientId').val()
        doForm([`/hc/patients/${id}`, formData], () => {
            window.location.href = '/hc/patient'
        })
    })
})