//<![CDATA[

function addResource() {

	$('#error-server').html('');
	$('#error-server').hide();
	$('#error-client').html('');

	var flagContinue = false;

	flagContinue = isEmpty('name',
			'El Nombre es requerido. Debe tener entre 2 y 16 caracteres.')
			& isEmpty('path',
					'La Ruta es requerida. Debe tener entre 2 y 30 caracteres.');

	if (flagContinue) {
		$('#error-client').hide();
		$('#addResource-form').submit();
	}

}

function editResource(id) {

	$('#edit-Resource-' + id + '-form').submit();

}

function isEmpty(fieldName, message) {
	var field = $('#' + fieldName).val();
	if (field == '') {
		$('#div-' + fieldName).addClass('has-error');
		$('#error-client').append('<p>' + message + '</p>');
		$('#error-client').show();
		return false;
	} else {
		$('#div-' + fieldName).removeClass('has-error');
		return true;
	}
}

function showModalDelete(idModal) {
	$('#modal-delete-' + idModal).modal();
}

// ]]>
