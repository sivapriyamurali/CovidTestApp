$(() => {
    const initTb = () => doGet(['/hc/test-centers'], (data) => {
        const html = data.map(({name, address, zipCode}) => (`<tr>
                            <td>${name}</td>
                            <td>${address}</td>
                            <td>${zipCode}</td>
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



