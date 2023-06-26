var input = "(Shell, Python, Perl, Java, Lisp, C++, APL, or whatever)\
	to produce a countof all the different words in a text file.\
	Use any definition of word that makes logical sense or makes\
	your job easy. The output might look like this:";
var counter = {};

input = input.replace(/[^a-zA-Z ]/gi, ''); // Remove parenthesis
input = input.split(' '); // Split words into an array

for(var i = 0; i < input.length; ++i) { // Count occurances
	if(!counter[input[i]]) {
		counter[input[i]] = 0;
	}
	counter[input[i]]++;
}

for(var key in counter) { // Output occurances
	console.log(key + ' ' + counter[key]);
}