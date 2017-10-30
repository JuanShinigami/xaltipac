function addGroup() {

	$('#error-server').html('');
	$('#error-server').hide();
	$('#error-client').html('');

	var flagContinue = false;

	flagContinue = isEmpty('name','El Nombre es requerido. Debe tener entre 2 y 16 caracteres.');

	if (flagContinue) {
		$('#error-client').hide();
		$('#addGroup-form').submit();
	}

}

function editGroup(id, name) {
	$("#id").val(id);
	$("#name").val(name);
	$("#exampleModalLabel1").text("Editar Grupo");
	$("#exampleModal1").modal();

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

function showModalAdd(){
	$("#id").val("");
	$("#name").val("");
	$("#exampleModalLabel1").text('Agregar Grupo');
	$("#exampleModal1").modal();
}

function addGroup(){
	
	var name = $("#name").val();
	var groupJSON = $('#add-group-form').serialize();
	//alert("Soy un shinigami: " + JSON.stringify(groupJSON));
	$.ajax({
		type : "POST",
		url : $('#add-group-form').attr('action'),
		data : ($('#add-group-form').serialize()),
		success : function(response) {
			if(response.isEditable === true){
				if(response.flagExist === true){
					alert("YA EXISTE");
				}else{
					if(response.flagSave === true){
						alert("Ya lo actualice :)");
						
					}else{
						alert("No se actualizó verificalo por favor :(");
					}
				}
			}else{
				if(response.flagExist === true){
					alert("YA EXISTE");
				}else{
					if(response.flagSave === true){
						alert("Ya lo agregué :)");
						
					}else{
						alert("No se guardo verificalo por favor :(");
					}
				}
			}
			
		},
		error : function(e) {
			//TODO: Action in error stage
		}
	});
}