var nodes = new vis.DataSet([
	{ id: 1, label: "Juan VACA" },
	{ id: 2, label: "Daniela GATO" },
	{ id: 3, label: "John HAMSTER" },
	{ id: 4, label: "Santiago PERRO" },
	{ id: 5, label: "Dogo PERRO" }
]);

var edges = new vis.DataSet([
	{ from: 1, to: 5, label: '5.274025' },
	{ from: 2, to: 1, label: '100000.0' },
	{ from: 3, to: 4, label: '4.1096816' },
	{ from: 4, to: 2, label: '2.0174346' },
	{ from: 5, to: 4, label: '1.0' }
]);

var container = document.getElementById("mynetwork");

var data = {
	nodes: nodes,
	edges:edges
};

var options = {};

var network = new vis.Network(container, data, options);