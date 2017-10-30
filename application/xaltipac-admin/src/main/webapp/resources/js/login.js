


$(document).ready(function() {
	$(document).keypress(function(e) {
	    if(e.which == 13) {
	    	$('#loginForm').submit(function(e) { 
	 		   e.preventDefault() ;
	 		    e.stopPropagation();
	 		});
	    	$('#loginFormIde').submit(function(e) { 
	 		   e.preventDefault() ;
	 		    e.stopPropagation();
	 		});
	    }
	});
});

function newSubmit(){
	$( "#listQuestions" ).removeClass( "has-error" );
	  $('#errorMessage').hide();
	  $('#errorMessage').html("");
	  $('#successMessage').hide();
	  $('#successMessage').html("");
	var campos = $('#loginForm').serialize();
	var urlPost = "recoverPassword";
	$.ajax({
		  type: "post",
		  url: urlPost,
		  cache: false,    
		  data:campos,
		  success: function(response){
			  if(response == 'showQuestions'){
				  //Buscamos las preguntas
				  $( "#formIde" ).removeClass( "has-error" );
				  $('#errorMessage').hide();
				  searchQuestions()
			  }else if(response == 'sendSuper'){
				  $( "#formIde" ).removeClass( "has-error" );
				  $('#errorMessage').hide();
				  $('#successMessage').html("");
				  $('#successMessage').css({"display":""})
				  $('#successMessage').html("La contrase\u00f1a se ha restablecido correctamente");
				  $('#loginForm')[0].reset();
			  }else{
				//Mostramos mensaje de error
				  $( "#formIde" ).addClass( "has-error" );
				  $('#errorMessage').html("");
				  $('#listQuestions').hide();
				  $('#errorMessage').css({"display":""})
				  $('#errorMessage').html(response);
			  }
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });
}

function searchQuestions(){
	$( "#listQuestions" ).removeClass( "has-error" );
	  $('#errorMessage').hide();
	  $('#errorMessage').html("");
	  $('#successMessage').hide();
	  $('#successMessage').html("");
	var campos = $('#loginForm').serialize();
	var urlPost = "recoverPasswordQ";
	var selectQuestion = '<label for="question" class="col-sm-3 control-label">Pregunta:</label>';
	selectQuestion += "<select class='form-control' style='width: 225px;margin-bottom:15px;' name='selectQuestion' id='selectQuestion'><option value='0'>Seleccione una pregunta</option>";
	$.ajax({
		  type: "post",
		  url: urlPost,
		  cache: false,    
		  data:campos,
		  success: function(response, status, xhr){
			  //alert(xhr.getResponseHeader("content-type"));
			  $.each(response, function(index, element) {
				  selectQuestion+="<option value="+element.id+">"+element.title+"</option>";
		        });
			  selectQuestion+="</select>";
			  selectQuestion+='<label for="answer" class="col-sm-3 control-label">Respuesta:</label><div class="col-sm-9"><input type="text" class="form-control" name="answer"/></div>';
			  //selectQuestion+='<div class="col-sm-offset-2 col-sm-10 text-right"><button type="button" onclick="validQuestions();" class="btn btn-primary" style="width:70px !important;height:35px;margin-top:15px;">Recuperar Contraseña</button></div>';
			  selectQuestion+='<div class="col-sm-offset-2 col-sm-10 text-right" style="margin-top:15px;"><p><a href="#" onclick="validQuestions();" class="btn-blue" style="margin-top:15px;" id="submitAnswer">Recuperar Contraseña</a></p></div>';
			  $('#listQuestions').show();
			  $('#listQuestions').html(selectQuestion);
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });
}

function validQuestions(){
	var campos = $('#loginForm').serialize();
	var urlPost = "recoverPasswordR";
	$.ajax({
		  type: "post",
		  url: urlPost,
		  cache: false,    
		  data:campos,
		  success: function(response, status, xhr){
			  if(response == "success"){
				  //El email se envió correctamente
				  $( "#listQuestions" ).removeClass( "has-error" );
				  $('#errorMessage').hide();
				  $('#successMessage').html("");
				  $('#successMessage').css({"display":""})
				  $('#successMessage').html("La contrase\u00f1a se ha restablecido correctamente");
				  $('#listQuestions').html("");
				  $('#loginForm')[0].reset();
			  }else{
				  $( "#listQuestions" ).addClass( "has-error" );
				  $('#errorMessage').html("");
				  $('#errorMessage').css({"display":""})
				  $('#errorMessage').html(response);
			  }
			 
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });
}

function newSubmitIdentifier(){
	$( "#loginFormIde" ).removeClass( "has-error" );
	  $('#errorMessage').hide();
	  $('#errorMessage').html("");
	  $('#successMessage').hide();
	  $('#successMessage').html("");
	var campos = $('#loginFormIde').serialize();
	var urlPost = "recuperarIdentificador";
	$.ajax({
		  type: "post",
		  url: urlPost,
		  cache: false,    
		  data:campos,
		  success: function(response, status, xhr){
			  if(response == "success"){
				  //El email se envió correctamente
				  $( "#loginFormIde" ).removeClass( "has-error" );
				  $('#errorMessage').hide();
				  $('#successMessage').html("");
				  $('#successMessage').css({"display":""})
				  $('#successMessage').html("El correo con el identificador se ha enviado correctamente");
				  $('#loginFormIde')[0].reset();
			  }else{
				  $( "#loginFormIde" ).addClass( "has-error" );
				  $('#errorMessage').html("");
				  $('#errorMessage').css({"display":""})
				  $('#errorMessage').html(response);
			  }
			 
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });
}