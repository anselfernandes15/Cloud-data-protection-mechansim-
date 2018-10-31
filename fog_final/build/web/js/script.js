$(function(){
    /**
     * loading validation engine in all forms
     */
    $('form').validationEngine();
    /**
     * when account type is selected
     */
    $('#accountType').change(function(){
        val = $(this).val();
        //hidding tab 2 for for all
        $('#customer2Tab').removeAttr('data-toggle');
        if(val !== 'Single'){
            $('#customer2Tab').attr({
                'data-toggle':'tab'
            });
        }
    }).trigger('change');
});