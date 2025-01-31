var nodes = new vis.DataSet([
	{ id: 1, label: "nombre: sd, direccion: dsfs" },
	{ id: 2, label: "nombre: ssdfdsf, direccion: sdfsdfsd" },
	{ id: 3, label: "nombre: sdfdssdfsdfsdf, direccion: sdfsdfsd" }
]);

var edges = new vis.DataSet([
	{ from: 1, to: 2, label: '2.7412603' },
	{ from: 2, to: 2, label: '0.0' },
	{ from: 2, to: 1, label: '0.0' },
	{ from: 2, to: 3, label: '2.3719192' },
	{ from: 3, to: 2, label: '246.13599' }
]);

var container = document.getElementById("mynetwork");

var data = {
	nodes: nodes,
	edges:edges
};

var options = {};

var network = new vis.Network(container, data, options);