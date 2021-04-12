$(function () {
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