(function($) {
	
	var s;
	
	function pagination(toPage) {
		var url = s.controller + "?page=" + toPage + "&size=" + s.size;
		if(s.filter.length > 0)
			url += "&filter=" + s.filter;		
		window.location = url;
	}

	function filter() {
		s.filter = $("#filterText").val();
		pagination(1);
	}

	function filterInput(event) {
		var tecla = document.all ? event.keyCode : event.which;
		if (tecla == 13) {
			filter();
		}
	}

	function filterButton() {
		filter();
	}

	function moveForward() {
		pagination(s.page + 1);
	}

	function moveBackward() {
		pagination(s.page - 1);
	}

	function moveFirstPage(){
		pagination(1);
	}

	function moveLastPage() {
		pagination(s.totalPages);
	}
	
	function movePage() {
		pagination($(this).text());
	}
	
	$.fn.paginationcontrol = function(options) {
		s = $.extend({
			controller: '/',
			page: 1,
			totalPages: 1,
			size: 10,
			filter: ''
		}, options);

		$('#filterText').val(s.filter);
		$('#filterSize').val(s.size);
		
		$(this).find('a.pagination-control-lastpage').click(moveLastPage);
		$(this).find('a.pagination-control-forward').click(moveForward);
		$(this).find('a.pagination-control-backward').click(moveBackward);
		$(this).find('a.pagination-control-firstpage').click(moveFirstPage);
		$(this).find('a.pagination-control-page').click(movePage);
		
		$('#filterText').keypress(filterInput);
		$('#filterButton').click(filterButton);
		$('#filterSize').change(function() {
			s.size = $('#filterSize option:selected').val();
			pagination(1);
		});
	};
	
} (jQuery));