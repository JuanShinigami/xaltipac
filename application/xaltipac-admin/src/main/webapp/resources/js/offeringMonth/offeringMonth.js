//<![CDATA[

function testPDF(){
	
	var pdf = new jsPDF('p', 'pt', 'letter');
	pdf.setLineWidth(1);
	pdf.rect(10, 10, 592, 772);
	var logo = new Image();
	logo.src = 'http://localhost:8080/xaltipac-admin/resources/images/pdfLLDM.jpg';
	pdf.addImage(logo, 'JPEG', 20, 30, 70, 60);
	pdf.setFont("times");
	pdf.setFontType("bolditalic");
	pdf.setFontSize(14);
	pdf.text( 'Jurisdicción Centro de la Iglesia del Dios Vivo, Columna y Apoyo de la Verdad,\nLa Luz del Mindo, A.R', 320, 25, 'center' );
	pdf.setFontType("italic");
	pdf.setFontSize(8);
	pdf.text('S.G.A.R./7:1/93                                    R.F.C JCD 940707 B87', 320, 55, 'center');
	pdf.setFont("arial");
	pdf.setFontType("normal");
	pdf.setFontSize(8);
	pdf.text('AVE. ALFREDO ROBLES DOMÍNGUEZ No.224, COL. VALLEJO, DEL. GUSTAVO A. MADERO, MÉXICO, D.F.', 320, 75, 'center');
	pdf.setFontSize(9);
	pdf.setFontType("bold");
	pdf.text('I N F O R M E   F I N A N C I E R O   M E N S U A L', 320, 95, 'center');
	pdf.setFontType("normal");
	pdf.setFontSize(8);
	pdf.text('INFORMACIÓN FINANCIERA DEL MES DE: ', 20, 110);
	
	pdf.save("Test.pdf");
//	var doc = new jsPDF("p", "pt", "letter"),
//	
//	source = $("#template_invoice")[0],
//    margins = {
//      top: 30,
//      bottom: 5,
//      left: 40,
//      width: 522
//    };
//    doc.fromHTML(
//      source, // HTML string or DOM elem ref.
//      margins.left, // x coord
//      margins.top, {
//        // y coord
//        width: margins.width // max width of content on PDF
//      },
//      function(dispose) {
//        // dispose: object with X, Y of the last line add to the PDF
//        //          this allow the insertion of new lines after html
////    	  var logo = new Image();
////    		logo.src = 'http://localhost:8080/xaltipac-admin/resources/images/pdfLLDM.jpg';
//    		
//    		//doc.addImage(logo, 'JPEG', 20, 20, 60, 60);
//    		//doc.setFontSize(18);
////    		doc.setFont("Arial");
////    		doc.setFontType("bold");
////    		
////    		doc.text(75, 30, 'Jurisdicción Centro de la Iglesia del Dios Vivo, Columna y Apoyo de la Verdad,');
////    		doc.text(75, 30, 'La Luz del Mundo, A.R.');
//    		
//    	  doc.setLineWidth(1);
//   		doc.rect(10, 10, 592, 772);
//        doc.save("Test.pdf");
//      },
//      margins
//    );
}

$(".btn").click(function() {
    
  });

// ]]>
