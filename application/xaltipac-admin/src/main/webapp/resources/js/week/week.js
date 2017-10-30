//<![CDATA[
 
function addWeek() {
	
	$('#error-server').html('');
	$('#error-server').hide();
	$('#error-client').html('');
	
	var flagContinue = false;
	
	flagContinue = isEmpty('day', 'El día para el registro es requerido.');
    
    if(flagContinue){
    	//alert("Estoy listo para guardar");
		$('#add-week-form').submit();
    }
	
}

function editWeek(id) {

	//$('#edit-week-' + id + '-form').submit();

}

function isEmpty (fieldName, message){
	var field = $('#' + fieldName).val();
	if(field == ''){
		$('#div-' + fieldName).addClass('has-error');
		$('#error-client').show();
		$('#error-client').append('<p>' + message + '</p>');
		return false;
	}else{
		if($('#div-' + fieldName).attr('class') == 'col-lg-8 has-error'){
			$('#div-' + fieldName).removeClass('has-error');	
		}
		$('#error-client').hide();
		return true;
	}
}


function showModalDelete(idModal){
	$('#modal-delete-' + idModal).modal();
}

function registerOfferings(day){
	//alert("Estoy listo para registrar las ofrendas");
	$('#register-offering-'+day+'-form').submit();
}

function showModalCloseWeek(id){
	alert("Estas seguro que lo vas a eliminar?");
}