//<![CDATA[
 
function addProfile() {
	
	$('#error-server').html('');
	$('#error-server').hide();
	$('#error-client').html('');
	
	var flagContinue = false;
	
	flagContinue = isEmpty('name', 'El Nombre es requerido. Debe tener entre 2 y 16 caracteres.');
    
    if(flagContinue){
		$('#add-profile-form').submit();
    }
	
}

function editProfile(id) {

	$('#edit-profile-' + id + '-form').submit();

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

function buscarUsuarios(id){
	
	$('#success-server').html('');
	$('#success-server').hide();
	$('#error-client').html('');
	$('#error-client').hide();
	
	var action = $('#form-delete-' + id).attr("action");
	
	var urlPost = action + "/obtenerUsuariosPerfil";
	var datas=$('#form-delete-' + id).serialize();
	$('#inner-users-' + id).html('');
	$.ajax({
		  type: "POST",
		  url: urlPost,
		  cache: false,
		  data:datas,
		  success: function(response, status, xhr){
			  
			  if(response.STATUS == 'true'){
				  if(response.statusProfileLoged == 'true'){
					  
					  $('#modal-current-users-' + id).modal();
					  
				  }else if (response.statusProfileLoged == 'false'){
					 
					  $.each(response.users, function(index, element) {
						  $('#inner-users-' + id).append('<p>- ' + element + '</p>');
				        });
					  
					  $('#modal-users-' + id).modal();
					  
				  }
				  
				 // $('#error-client').append('<p>No es posible eliminar el perfil ya que se encuentra asociado a los siguientes usuarios:</p>');
				  /*$.each(response.users, function(index, element) {
					  $('#error-client').append('<p>- ' + element + '</p>');
			        });
				  */
				 // $('#error-client').append('<p>Por favor modifique el campo &quot;Perfiles&quot; en los datos de los usuarios y vuelva a intentarlo</p>');
				  //$('#error-client').show();
				  
			  }else if(response.STATUS == 'false'){
				  showModalDelete(id);
			  }else{
				  $('#error-client').show();
					$('#error-client').append('<p>Error intentelo mas tarde</p>');
			  }
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });
}






	//]]>
