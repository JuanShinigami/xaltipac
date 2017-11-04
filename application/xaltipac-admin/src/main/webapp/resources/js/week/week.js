//<![CDATA[
 
var countAnonymous = 0;
var validateSeacrh = false;
var stringOptionSelectc = '';
//var stringOptionSelect = '';

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
	//alert("Estas seguro que lo vas a eliminar?");
	$('#weekId').val(id);
	countAnonymous = 0;
	$('#modal-add-anonymous').modal({backdrop: 'static', keyboard: false});
	validateSeacrh = false;
	$('#content-model-add-anonymous').html('');
	$('#alert-anonymous').hide();
	$('#index-anonymous').val('');
	$('#anonymousWeek').val('');
}

function addAnonymous(){
	countAnonymous++;
	$.ajax({
		type : "POST",
		url : $('#serach-offering-week').attr('action'),
		data : ($('#serach-offering-week').serialize()),
		success : function(response) {
			validateSeacrh = true;
			response.offeringDetails;
			stringOptionSelectc = stringOptionSelect(response.offeringDetails);
			//$('#content-model-add-anonymous').append('<div id="content-div-anonymous-'+countAnonymous+'">');
			$('#content-model-add-anonymous').append('<div class="col-12 col-sm-4 col-md-2" id="div-label-'+countAnonymous+'"><label class="anonymous-label">Anónimo '+countAnonymous+'</label></div>');
			$('#content-model-add-anonymous').append('<div class="col-12 col-sm-3 col-md-4" id="div-input-'+countAnonymous+'"><div class="form-group"><label for="anonymous-'+countAnonymous+'">(*) Cantidad:</label><input type="number" class="form-control" id="anonymous-'+countAnonymous+'" name="anonymous-'+countAnonymous+'" /></div></div>');
			$('#content-model-add-anonymous').append('<div class="col-10 col-sm-3 col-md-5" id="div-select-'+countAnonymous+'">'
					+ '<div class="form-group"><label for="optionOffering-'+countAnonymous+'">(*) Ofrenda:</label>'
					+ '<select class="form-control" id="optionOffering-'+countAnonymous+'" name="optionOffering-'+countAnonymous+'">'
					+ stringOptionSelectc
					+ '</select></div></div>');
			$('#content-model-add-anonymous').append('<div class="col-1 col-sm-1 col-md-1 div-delete" id="div-delete-'+countAnonymous+'"><a href="javascript:{}" onclick="javascript:deleteAnonymous('+countAnonymous+');" id="a-add-anonymous" class="btn btn-danger">-</a></div>');
			//$('#content-model-add-anonymous').append('</div>');
		},
		error : function(e) {
			// TODO: Action in error stage
		}
	});
	
	
	//var stringText = stringOptionSelect();
	
	
	
	
//	countAnonymous++;
//	var index = $('#index-anonymous').val();
//	$('#index-anonymous').val(index + countAnonymous + ",");
	
}

function saveOfferingWeek(){
	var flagContinue = true;
	var indexAnonymous = '';
	$("input[id^='anonymous-']").each(function(index, element) {
		var val = $(element).attr("id");
		//alert(val.substring(10));
		indexAnonymous += val.substring(10) + ',';
		
		if($(element).val() === ""){
			flagContinue = false;
		}
	});
	$("select[id^='optionOffering-']").each(function(index, element) {
		if($(element).val() === "0"){
			flagContinue = false;
		}
	});
	//alert("Soy la semana --- " + $('#weekId').val());
	
	//alert(indexAnonymous);
	if(flagContinue === true){
		$('#alert-anonymous').hide();
		alert("Podemos continuar");
		$('#index-anonymous').val(indexAnonymous.substring(0,(indexAnonymous.length - 1)));
		$('#anonymousWeek').val($('#weekId').val());
		$.ajax({
			type : "POST",
			url : $('#close-week-form').attr('action'),
			data : ($('#close-week-form').serialize()),
			success : function(response) {
				
			},
			error : function(e) {
				// TODO: Action in error stage
			}
		});
	}else{
		$('#alert-anonymous').show();
	}
	
	
}

function stringOptionSelect(offeringJSON){
	var stringOption = '<option value="0">Selecciona...</option>';
	$.each(offeringJSON, function(index,element) {
		//alert(element.offering.id);
		stringOption += '<option value="'+element.offering.id+'">'+element.offering.name+'</option>';
	});
	return stringOption;
	
}

function deleteAnonymous(id){
	//alert("ID DEL DIV ----- " + id);
	$('#div-label-' + id).remove();
	$('#div-input-' + id).remove();
	$('#div-select-' + id).remove();
	$('#div-delete-' + id).remove();
}

function generetedPDF(id){
	$('#weekPDFId').val(id);
	//$('#prueba-pdf-tabla').hide();
//	html2canvas(document.getElementById("prueba-pdf-tabla"), {
//		onrendered: function(canvas){
//			var img = canvas.toDataURL("image/png");
//			var doc = new jsPDF();
//			doc.addImage(img, 'JPEG', 10, 10);
//			doc.save('test.pdf');
//		}
//	});
	var date = new Date();
	var dateFormat = date.getUTCDate() + "-" + date.getUTCMonth() + "-" + date.getUTCFullYear();
	
	var options = {weekday: "long", year: "numeric", month: "long", day: "numeric"};
	//createPDF(dateFormat, date.toLocaleDateString("es-ES", options), 5);
	//alert(date.toLocaleDateString("es-ES", options));
	 $.ajax({
		type : "POST",
		url : $('#generate-pdf-week-form').attr('action'),
		data : ($('#generate-pdf-week-form').serialize()),
		success : function(response) {

		},
		error : function(e) {
			// TODO: Action in error stage
		}
	});
}

function createPDF(nameReport, date, countColums){
	
	var doc = new jsPDF();
	
	var logo = new Image();
	logo.src = 'http://localhost:8080/xaltipac-admin/resources/images/logolldm-1.png';
	//(x, y , ancho, alto)
	doc.addImage(logo, 'JPEG', 10, 10, 30, 30);
	doc.setFontSize(18);
	doc.setFont("Arial");
	doc.setFontType("bold");
	//(40)
	doc.text(75, 25, 'La Luz Del Mundo');
	doc.setFontSize(10);
	doc.setFontType("100");
	doc.text(155, 35, date);
//	var imageTest = new Image();
//	imageTest.src = 'http://localhost:8080/xaltipac-admin/resources/images/pets.png';
//	doc.addImage(imageTest, 'JPEG', 10, 50, 190, 100);
	doc.setFontSize(14);
	doc.setFontType("bold");
	doc.text(10, 50, 'Nombre');
	var widthCol = repartColmn(countColums);
	for(x = 1; x <= countColums; x++){
		doc.text(60 + (x * widthCol), 50, 'Col-' + x);
	}
	doc.setFontSize(12);
	doc.setFontType("100");
	
	
	doc.save(nameReport + '.pdf');
}

function repartColmn(nColumn){
	var part = 120 / nColumn;
	return part;
}