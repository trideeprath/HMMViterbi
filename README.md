# Part Of Speech Tagger Implementation
Implementation of a Part Of Speech Tagger based on Hidden Markov Model and Viterbi Algorithm.
Uses machine learning on the training set to predict the POS of the new sentences.

Accuracy = 91 %

## Parts of Speech tags
C Coordinating conjunction or Cardinal number

D Determiner

E Existential there

F Foreign word

I Preposition or subordinating conjunction

J Adjective

M Modal (could, would, must, can, might . . .)

N Noun

P Pronoun or Possessive ending Predeterminer

R Adverb or Particle

S Symbol, mathematical (rare)

T The word

U Interjection (rare)

V Verb

W wh -word (question word)

## Training is done over 10,000 sentences.
(Corpus Link)[http://www.cs.jhu.edu/~jason/465/hw-hmm/data/entrain]

###Input sentence: 
It is the right-wing guerrillas who are with the traffickers, not the left wing.

###Output:

It/P
is/V
the/D
right-wing/J
guerrillas/N
who/W
are/V
aligned/V
with/I
the/D
drug/N
traffickers/N
,/,
not/R
the/D
left/J
wing/N
./.
