grammar Grammar;

start: jobOpeningReference requirement+;

requirement: START_REQUIREMENT (specificRequirement | minimumRequirement) END_REQUIREMENT;

jobOpeningReference: START_REFERENCE STRING END_REFERENCE;

body: START_REQUIREMENT_BODY STRING END_REQUIREMENT_BODY;
requirementAnswer: START_ANSWER (NUMBER (','NUMBER)* | STRING (',' STRING)*) END_ANSWER;

minimumRequirement: START_TYPE 'minimum' END_TYPE body requirementAnswer;

specificRequirement: START_TYPE 'specific' END_TYPE body requirementAnswer;

// ----- TOKENS -----

START_REFERENCE:            '@start-reference@';
END_REFERENCE:              '@end-reference@';

START_TYPE:                 '@start-type@';
END_TYPE:                   '@end-type@';

START_ANSWER:               '@start-answer@';
END_ANSWER:                 '@end-answer@';

START_REQUIREMENT:          '@start-requirement@';
END_REQUIREMENT:            '@end-requirement@';

START_REQUIREMENT_BODY:     '@start-requirement-body@';
END_REQUIREMENT_BODY:       '@end-requirement-body@';

STRING:
	'"' ('\\' [\\"] | ~[\\",])* '"'
	| '"' ( '\\' [\\n] | ~[\\",])* '"';

NUMBER:                 REAL_NUMBER | INTEGER;
REAL_NUMBER:	        [0-9]+ '.' [0-9]+;
INTEGER:			    [0-9]+;
DATE:                   [0-3][0-9]'/'[0-1][0-9]'/'[0-9][0-9][0-9][0-9];
TIME:                   [0-2][0-9]':'[0-5][0-9](':'[0-5][0-9])?;
SCALE:                  '['NUMBER+ '-' NUMBER+ ']';
BOOLEAN:                (([tT][rR][uU][eE]) | ([fF][aA][lL][sS][eE]));

// Skip spaces, tabs and newlines
WS: [ \t\n\r]+ -> skip;

// Skip comments
COMMENT: '//' ~[\r\n]* -> skip;
