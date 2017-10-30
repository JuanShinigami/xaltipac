//<![CDATA[

$(document).ready(function() { 
	$('#active').val(0);
	if($('#edit').val() === "true"){
		//alert($('#idstate').val()+" "+$('#iddistrict').val()+" "+$('#idneigh').val());
		//alert("Soy edición");
		//alert($('#idactive').val());
		if($('#idactive').val() === "1"){
			alert("soy --- 1");
			$('#active').val(1);
			$( "#isActive" ).prop( "checked", true );
		}else{
			$('#active').val(0);
			$( "#isActive" ).prop( "checked", false );
		}
		//alert($('#active').val());
		$('#gender').val($('#idgender').val());
		$('#optionGender').val($('#idgender').val());
		$('#optionStates').val($('#idstate').val());
		$('#newstate').val($('#idstate').val());
		$('#optionDistricts').html('');
		$.ajax({
			type : "POST",
			url : $('#search-district-form').attr('action'),
			data : ($('#search-district-form').serialize()),
			success : function(response) {
				$('#optionDistricts').append("<option value='0'>Selecciona...</option>");
				$.each(response.districts, function(index,element) {
					$('#optionDistricts').append("<option value='"+element.id+"'>"+element.name+"</option>");
				});
				$('#optionDistricts').val($('#iddistrict').val());
			},
			error : function(e) {
				// TODO: Action in error stage
			}
		});
		
		
		$('#newdistrict').val($('#iddistrict').val());
		$('#optionNeighborhood').html('');
		$.ajax({
			type : "POST",
			url : $('#search-neighborhood-form').attr('action'),
			data : ($('#search-neighborhood-form').serialize()),
			success : function(response) {
				$('#optionNeighborhood').append("<option value='0'>Selecciona...</option>");
				$.each(response.neighborhoods, function(index,element) {
					$('#optionNeighborhood').append("<option value='"+element.id+"'>"+element.colony+"</option>");
				});
				$('#optionNeighborhood').val($('#idneigh').val());
			},
			error : function(e) {
				// TODO: Action in error stage
			}
		});
		$('#optionStates').val($('#idstate').val());
	}
	
	$('#optionStates').change(function() {
//		  alert( $(this).val() );
		  $('#newstate').val($(this).val());
		  
		  $('#optionDistricts').html('');
		  $.ajax({
				type : "POST",
				url : $('#search-district-form').attr('action'),
				data : ($('#search-district-form').serialize()),
				success : function(response) {
					//alert("Respondi");
					$('#optionDistricts').append("<option value='0'>Selecciona...</option>");
					$.each(response.districts, function(index,element) {
						//alert("DISTRITO ---- " + element.name);
						$('#optionDistricts').append("<option value='"+element.id+"'>"+element.name+"</option>");
					});
				},
				error : function(e) {
					// TODO: Action in error stage
				}
			});
	});
	// colocar que no es activo
	//$('#active').val(0);
	
	$( '#optionDistricts' ).change(function() {
//		  alert( $(this).val() );
		  $('#newdistrict').val($(this).val());
		  $('#optionNeighborhood').html('');
		  $.ajax({
				type : "POST",
				url : $('#search-neighborhood-form').attr('action'),
				data : ($('#search-neighborhood-form').serialize()),
				success : function(response) {
					$('#optionNeighborhood').append("<option value='0'>Selecciona...</option>");
					$.each(response.neighborhoods, function(index,element) {
						$('#optionNeighborhood').append("<option value='"+element.id+"'>"+element.colony+"</option>");
					});

				},
				error : function(e) {
					// TODO: Action in error stage
				}
			});
	});
	
	$( "#isActive" ).change(function() {
		if( $(this).is(':checked') ) {
	        // Hacer algo si el checkbox ha sido seleccionado
			$('#active').val(1);
	    } else {
	        // Hacer algo si el checkbox ha sido deseleccionado
	    	$('#active').val(0);
	    }
	});
	
	$('#optionGender').change(function(){
		//alert($(this).val());
		$('#gender').val($(this).val());
		//alert($('#gender').val());
	});
	
	$("#name").blur(function(){
        
    });
});
 

	function addUser(){
		
		$('#error-server').html('');
		$('#error-server').hide();
		$('#error-client').html(''); 
		
		var idUser = $('#userDetailsId').val();
				
		var flagContinue = false;
		
		var password = $('#password').val();
		//var passwordConfirm = $('#passwordConfirm').val();
		
		var genderId = $('#optionGender option:selected').val();
		var profileId = $('#optionProfiles option:selected').val();
		var groupId = $('#optionGroups option:selected').val();
		var neighborhoodId = $('#optionNeighborhood option:selected').val();
		
		
		flagContinue = isEmpty('nInside', 'El número interior es requerido. Debe tener números.') &
        isEmpty('name', 'El Nombre es requerido. Debe tener entre 1 y 55 caracteres.') &
        isEmpty('fatherLastName', 'El Apellido paterno es requerido. Debe tener entre 1 y 75 caracteres.') &
        isEmpty('motherLastName', 'El Apellido materno es requerido.Debe tener entre 1 y 50 caracteres.') &
        isEmpty('birthdate', 'La fecha de nacimiento es requerida. Debe tener este formato dd/mm/aaaa.') &
        isEmpty('street', 'La calle es requerida. Debe tener este entre 1 y 50 caracteres.') &
        isEmpty('nExterior', 'El número exterior es requerido. Debe tener números.') &
        isEmpty('nInside', 'El número interior es requerido. Debe tener números.') &
        isEmpty('identifier','El Identificador es requerido. Debe tener entre 4 y 16 caracteres.');
    
		if("" == idUser){
			flagContinue = isEmpty('password', 'La Contrase&ntilde;a es requerida. Debe tener entre 4 y 16 caracteres.');
		}
	
		if(profileId == 0){
			$('#error-client').show();
			$('#error-client').append('<p>Debe seleccionar un perfil.</p>');
			flagContinue= false;
		}
		
		if(genderId == 0){
			$('#error-client').show();
			$('#error-client').append('<p>Debe seleccionar un genero.</p>');
			flagContinue= false;
		}
		
		if(groupId == 0){
			$('#error-client').show();
			$('#error-client').append('<p>Debe seleccionar un grupo.</p>');
			flagContinue= false;
		}
		
		if(neighborhoodId == 0){
			$('#error-client').show();
			$('#error-client').append('<p>Debe seleccionar una colonia.</p>');
			flagContinue= false;
		}
        
        if(flagContinue){
        	if($('#edit').val() === "true"){
        		//alert("Soy editar");
        		$('#addUser-form').submit();
        	}else{
        		if(password != ""){
        			$('#addUser-form').submit();
        		}else{
        			$('#error-client').show();
        			$('#error-client').append('<p>Debe ingresar una contreseña.</p>');
        		}
        	}
        }
		
	}
	
	
	function validPassword (password, passwordConfirm){
		if(password != passwordConfirm){
			$('#error-client').show();
			$('#error-client').append('<p>La Contrase&ntilde;a y la confirmacion de la contrase&ntilde;a no coinciden.</p>');
			return false;
		}else{
			if(password == $('#identifier').val()){
				$('#error-client').show();
				$('#error-client').append('<p>La Contrase&ntilde;a no puede ser igual al identificador.</p>');
				return false;
			}
			if (!/^((?=.*[0-9])(?=.*[a-zA-Z]{3})(\w|[!@#$%&?_;,\-\+\*\"\.]){4,20})*$/.test(password)) {
				$('#error-client').show();
				$('#error-client').append('<p>La Contraseña es incorrecta. Debe tener al menos 3 caracteres y un número.</p>');
				return false
			}
			
			$('#error-client').hide();
			return true;
		}
	}
	

	function isEmpty (fieldName, message){
		var field = $('#' + fieldName).val();
		if(field == ''){
			$('#div-' + fieldName).addClass('has-error');
			$('#error-client').show();
			$('#error-client').append('<p>' + message + '</p>');
			return false;
		}else{
			if($('#div-' + fieldName).attr('class') == 'col-sm-5 has-error'){
				$('#div-' + fieldName).removeClass('has-error');	
			}
			$('#error-client').hide();
			return true;
		}
	}
	
	function modalSubmit(){
		$('#addUser-form').submit();
	}
	
	
	function showModalDelete(idModal){
		$('#modal-delete-' + idModal).modal();
	}
	
	
	function editUser(id) {

		$('#edit-user-' + id + '-form').submit();

	}
	
	
	function genereted(){
		//alert($('#birthdate').val());
		var name = $("#name").val();
		var lastName = $("#fatherLastName").val();
        var currentDate = new Date();
        var time = currentDate.getTime().toString();
        var res = time.substring(10, 5);
        $("#identifier").val(name+lastName+res);
        $("#password").val(name+lastName+res);
	}
	//]]>