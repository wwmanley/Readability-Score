# Readability-Score
This project is an assignment from [Hyperskill's project list](https://hyperskill.org/projects/39). The concept of this program is to score how "readable" a given text file is. 

## Demo

![Gif of program executing](https://i.imgur.com/J6qYm2x.gif)

# Installation

1. Navigate to where you want to install the project
2. ``` git clone https://github.com/wwmanley/Readability-Score ```
3. Navigate into the src folder
4. ``` javac Main.java ```
5. ``` java Main ```

## Algorithms

We were given four algorithms that score how readable the given file is. These algorithms will return a number between 1-14. The following chart can be used to determine what age group should be able to read the given text:

![Image of Readable Chart](https://i.imgur.com/bvUeAl3.png)

The four algorithms we used to determine the scores were the [Automated Readability Index Test](https://en.wikipedia.org/wiki/Automated_readability_index), the 
[Flesh-Kincaid Readability Test](https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests), the [Simple Measure of Gobbledgook Test](https://en.wikipedia.org/wiki/SMOG),
and the [Coleman-Liau Index Test](https://en.wikipedia.org/wiki/Coleman%E2%80%93Liau_index). Some of the requirements for these formulas inclue the number of sentences,
number of words, number of characters, number of syllables, and number of polysyllables within the text file.

## Syllables and Polysyllables

For this particular program, there are three rules to follow in order to determine how many syllables a given word is.

1. The number of vowels in a word is equal to the number of syllables.
2. If there are two vowels in a row, then that string of vowels will only count as one syllable.
3. If the last letter in a word is 'e', then do not count it is a vowel / syllable.

The theory behind this method doesn't work 100% of the time, for example the word "simple" would only count as one syllable with the given conditions. Fortunately this method is
accurate with the majority of words, so the results are still fairly accurate. 

The conditions for counting Polysyllables were very straightforward. In this program, every word that has more than two syllables is considered a polysyllable.
