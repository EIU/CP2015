
var problems = {};
var currentProb = null;
var inputs = [];
var currentInput = -1;
var expectedResult = '';
var currentResult = '';

function readline() {
    return inputs[currentInput++];
}

function print(s) {
    currentResult += s + '\n';
}

function randBetween(l, u) {
    return Math.floor(Math.random() * (u - l));
}

function addRuntimeRow() {
    var no = $('#Runtime tbody tr').length + 1;
    return $('<tr><td>' + no + '</td><td></td><td></td></tr>').appendTo('#Runtime tbody');
}

function registerProblem(probId, testcases, func) {
    problems[probId] = { testcases: testcases, func: func };
    $('#SelectProblem').append('<option value="' + probId + '">' + probId + '</option>');
}

function getCurrentProblem() {
    return problems[$('#SelectProblem').val()];
}

$('#SelectProblem').change(function () {
    $('#Runtime tbody').html('');

    var currentProb = getCurrentProblem();
    if (!currentProb) return;

    currentProb.testcases.forEach(function (test) {
        runProblem(test, currentProb.func);
    });
});

function runProblem(test, func) {

    inputs = [];
    currentInput = 0;

    var jqRow = addRuntimeRow();
    jqRow.find('td:eq(1)').append('<pre>' + test.input.replace(/\n\s*/g, '\n') + '</pre>');

    expectedResult = test.output;
    currentResult = '';

    test.input.split('\n').forEach(function (line) {
        line = line.trim();
        if (line) {
            inputs.push(line);
        }
    });

    var startTime = (new Date()).getTime();

    func();

    var endTime = (new Date()).getTime();

    jqRow.find('td:eq(2)').append('<pre>Result:\n' + currentResult + 'In ' + (endTime - startTime) + ' milliseconds \n--------------------------------\nExpected:\n' + expectedResult + '</pre>');

}