function searchOffering(idUser, name, lastName, lastNameMaternal){
	$('#contentOffering').html('');
	//	alert(name);
//	alert(lastName);
//	alert(lastNameMaternal);
	//alert("Usuario con el id ----- " + idUser);
	var weekId = $('#weekId').val();
	//alert("Semana con el id ------- " + weekId);
	$('#idWeek').val(weekId);
	$('#idUser').val(idUser);
	$('#titleModalOffering').text(name + " " + lastName + " " + lastNameMaternal);
	
	$.ajax({
		type : "POST",
		url : $('#search-offering-form').attr('action'),
		data : ($('#search-offering-form').serialize()),
		success : function(response) {
			if(response.isEmpty === false){
				$('#contentIndices').val(response.indices);
				$.each(response.offeringDetails, function(index,element) {
					$('#contentOffering').append('<div class="col-12 col-sm-12 col-md-12"><div class="form-group"><label for="name">'+element.offering.name+'</label> <input type="number" class="form-control" id="offering-'+element.id+'" name="offering-'+element.id+'" value="'+element.quantity+'" /> </div></div>');
					//$('#contentOffering').append('<p>'+element.offering.name+'</p><input id="offeing'+element.id+'" name="offeing'+element.id+'" value="0" />');
				});
				$('#modal-edit-offeringDetails').modal({backdrop: 'static', keyboard: false});
			}else{
				alert("No hay ofrendas a registrar.");
			}
		},
		error : function(e) {
			// TODO: Action in error stage
		}
	});
	
}

function addOfferingDetails(){
	$.ajax({
		type : "POST",
		url : $('#edit-offering-details-form').attr('action'),
		data : ($('#edit-offering-details-form').serialize()),
		success : function(response) {
			if(response.isEmpty === false){
				if(response.isSave === true){
					//alert("Lo guarde sin problemas");
					$('#modal-edit-offeringDetails').modal('toggle');
					
				}else{
					alert("Hay un problema para guardar las ofrendas.")
				}
			}else{
				alert("No hay ofrendas a registrar.");
			}
		},
		error : function(e) {
			// TODO: Action in error stage
		}
	});
}
