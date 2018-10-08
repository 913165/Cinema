/**
 * @author Robert Wilk Created on 1/20/2016.
 */

// Begin Something modal population and submit functions
var url = "/admin/";
var editModalTarget = url + "loadEntity/";
var deleteModalTarget = url + "loadEntity/";
var tableTarget = url + "loadSomethingTable/";

// Build the url for the Ajax request for Something.
function showEditModal(index) {
	var editUrl = editModalTarget + index;
	loadEntity(editUrl);
}

// Build the url for the Ajax request for Something.
function showDeleteModal(index) {
	var deleteUrl = deleteModalTarget + index;
	loadEntityForDelete(deleteUrl);
}

function showDeleteModal2(index) {
	$('#delete-id').val(index);
}

// Ajax request for Something to populate the modal form.
function loadEntity(url) {
	$.getJSON(url, {}, function(data) {
		populateModal(data, names);
	});
}

//Ajax request for Something to populate the modal form.
function loadEntityForDelete(url) {
	$.getJSON(url, {}, function(data) {
		populateModalForDelete(data, names);
	});
}


// Assign the data values to the modal form.
function populateModal(data) {
	$('#update-id').val(data.id);
	$('#update-title').val(data.title);
}

//Assign the data values to the modal form.
function populateModalForDelete(data) {
	$('#delete-id').val(data.id);
	$('#delete-title').val(data.title);
}


function clearModal() {
	$('#update-id').val('');
	$('#update-title').val('');
}

function closeModal(name) {
	$(name).modal('toggle');
}

function clearAndCloseModal(name) {
	clearModal();
	closeModal(name);
}

// POST the edits to Something to the server.
function postEdit() {
	var movie = $('#edit-form').serialize();
	var editUrl = url + 'update';
	$.post(editUrl, movie, function(data) {
		updateTable(data);
	});
	clearAndCloseModal('#umodal');
}

//POST the edits to Something to the server.
function postDelete() {
	var movie = $('#delete-form').serialize();
	var deleteUrl = url + 'delete';
	$.post(deleteUrl, movie, function(data) {
		updateTable(data);
	});
	clearAndCloseModal('#dmodal');
}

function deleteEntity(entity) {
	var input = $('#delete-id');
	var url = '' + entity + '/delete/' + input.val();
	$.post(url, 3, function(data) {
		 updateTable(data);
	});
	closeModal('#dmodal');
	input.val('');
}

function updateTable(data) {
	$.ajax({
		dataType : "json",
		url : tableTarget,
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		success : function(response) {
			$('#table-body').empty();
			$.each(response, function(i, e) {
				var end = e.id + ");'";
				var edit = "'showEditModal(" + end;
				var del = "'showDeleteModal(" + end;
				var row = $('<tr>').append(
						$('<td>').text(e.id),
						$('<td>').text(e.title),
						$('<td>').append(
								"<a data-toggle='modal' data-target='#umodal' onclick="
										+ edit + ">Edit</a>"),
						$('<td>').append(
								"<a data-toggle='modal' data-target='#dmodal' onclick="
										+ del + ">Delete</a>"));
				$('#movie-table').append(row);
			});
		}
	});
}
