/*
 * @author gfeng
 */

function validateForm() {
		
	var x = document.forms["createForm"]["title"].value;
	if (x == "") {
		alert("Title must be filled");
		return false;
	}

	var x = document.forms["createForm"]["desc"].value;
	if (x == "") {
		alert("Description must be filled");
		return false;
	}

	var x = document.forms["createForm"]["image"].value;
	if (x == "") {
		alert("Image must be filled");
		return false;
	}

	if (!isValidHttpUrl(x)) {
		alert("Image is invalid URL");
		return false;
	}

	return true;
}

function popupMessage(msg) {
	msg = msg.replace('[', '').replace(']', '').replace(', ', '\n');
	alert(msg);
}

function isValidHttpUrl(image) {
	let url;

	try {
		url = new URL(image);
	} catch (_) {
		return false;
	}

	return url.protocol === "http:" || url.protocol === "https:";
}

