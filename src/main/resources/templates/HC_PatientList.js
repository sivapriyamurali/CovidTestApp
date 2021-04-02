$(() => {
    const initTb = () => doGet(['/hc/patients'], (data) => {
        const html = data.map(({fullname, age='', email,id,image}) => (`<tr>
                            <td>${fullname}</td>
                            <td>${age}</td>
                            <td>${email}</td>
                            <td>${image?` <img class="img-report" src="data:image/png;base64, ${image}"  />`:''}</td>
                            <td><input type="radio" data-id="${id}" name="ops"/></td>
                         </tr>`)).join();
        $('#tb_id').html(html)
    })

    initTb();

    $(document).on('click', '#confirm_id', () => {
        console.info('confirm')
        const $center = $('#tb_id input[type=radio]:checked')
        if ($center.length !== 0) {
           const id =  $center.attr('data-id');
           window.location.href = `/hc/patient/update/${id}`
        }
    })
})
