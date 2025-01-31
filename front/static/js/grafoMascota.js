var nodes = new vis.DataSet([
	{ id: 1, label: "Daniela PERRO" },
	{ id: 2, label: "Final PERRO" },
	{ id: 3, label: "Santiago CABALLO" }
]);

var edges = new vis.DataSet([
	{ from: 1, to: 2, label: '1.0' },
	{ from: 3, to: 1, label: '100000.0' }
]);

var container = document.getElementById("mynetwork");

var data = {
	nodes: nodes,
	edges:edges
};

var options = {};

var network = new vis.Network(container, data, options);