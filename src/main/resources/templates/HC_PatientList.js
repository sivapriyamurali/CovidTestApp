$(() => {
    const initTb = () => doGet(['/hc/patients'], (data) => {
        const html = data.map(({fullname, age='', email}) => (`<tr>
                            <td>${fullname}</td>
                            <td>${age}</td>
                            <td>${email}</td>
                            <td><input type="radio" name="ops"/></td>
                         </tr>`)).join();
        $('#tb_id').html(html)
    })

    initTb();

    $('.ops').on('click', '#confirm_id', () => {
        const $center = $('#tb_id input[type=radio]:checked')
        if ($center.length !== 0) {
            const $name = $center.closest('tr').find('td').eq(0);
            alert($name.text())
        }
    })
})
