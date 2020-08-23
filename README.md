# Readability-Score
This project is an assignment from [Hyperskill's project list](https://hyperskill.org/projects/39). The concept of this program is to score how "readable" a given text file is. 
We were given four algorithms that score how readable the given file is. These algorithms will return a number between 1-14. We determine what age group can read the given file by
using the following chart:

![Image of Readable Chart](https://i.imgur.com/fLbwYwb.png)

The four algorithms we used to determine the scores were the [Automated Readability Index Test](https://en.wikipedia.org/wiki/Automated_readability_index), the 
[Flesh-Kincaid Readability Test](https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests), the [Simple Measure of Gobbledgook Test](https://en.wikipedia.org/wiki/SMOG),
and the [Coleman-Liau Index Test](https://en.wikipedia.org/wiki/Coleman%E2%80%93Liau_index). Some of the requirements for these formulas inclue the number of sentences,
number of words, number of characters, number of syllables, and number of polysyllables within the text file.
