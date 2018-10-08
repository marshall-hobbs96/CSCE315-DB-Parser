grammar Parser;

program : ((command | query) ';'*) ((command | query) ';'*)* EOF; //one wrapper to rule them all

//Simple spelling out of internal object names

query : relation_name  '<-' expr ';';

relation_name : identifier;

identifier : Alpha (Alpha | Digit)*;

Alpha : [a-zA-Z_]+;

Digit : [0-9];

expr : (atomic_expression | selection | projection | renaming | union | difference | product | natural_join);

atomic_expression : relation_name | '('expr')';		//smallest expression allowed. Doesn't include operators

WHITE_SPACE : ' '* -> skip;

//SEMICOLON : ';'* -> skip;

two_identifiers : identifier  identifier; 


//operations
selection : 'select' ('('condition')' | condition) atomic_expression;

condition : conjunction ('||' conjunction)*;

conjunction : comparison ('&&' comparison)*;

comparison : (operand op operand) | '('condition')';




op : ('==' | '!=' | '<' | '>' | '<=' | '=>');				//spells out available operators

operand : attribute_name | literal;

attribute_name : identifier;

literal : '"'? (Alpha+ | Digit+) '"'?;

projection : 'project'  '('attribute_list')' atomic_expression;

attribute_list : attribute_name (', ' attribute_name)*;

renaming : 'rename' '('attribute_list')' atomic_expression;

union : atomic_expression '+' atomic_expression;

difference : atomic_expression '-' atomic_expression;

product : atomic_expression '*' atomic_expression;

natural_join : atomic_expression '&' atomic_expression;






//Command parsing. Support for open, close, write, exit, show, create, update, insert, and delete

command : (open_cmd | close_cmd | write_cmd | exit_cmd | show_cmd | create_cmd | update_cmd | insert_cmd | delete_cmd);

open_cmd : 'OPEN' relation_name;		//opens relation

close_cmd : 'CLOSE' relation_name;		//closes relation

write_cmd : 'WRITE' relation_name;		//writes to relation

exit_cmd : 'EXIT';						//exits 

show_cmd : 'SHOW' atomic_expression;	//shows atomic expression

create_cmd : 'CREATE TABLE'  relation_name '('typed_attribute_list')'  'PRIMARY KEY'  '('attribute_list')';		//creates a new table, with a name and attribute list

update_cmd : ('UPDATE' relation_name 'SET' attribute_name '=' literal (',' literal)*) | ('INSERT INTO' relation_name 'VALUES FROM RELATION' expr);		//updates a relation's attribute with a new attribute. Alternatively, just adds another attribute to the relation

insert_cmd : ('INSERT INTO'  relation_name  ('VALUES FROM' | 'VALUES FROM RELATION') ( '(' (literal (', 'literal)*)')' | expr)); //inserts into a relations attribute a new value

delete_cmd : 'DELETE FROM' relation_name 'WHERE' condition; 		//deletes data from a attribute







//miscellanous stuff including list
typed_attribute_list : attribute_name  type (', '  attribute_name type)*;

type :  (('VARCHAR' '('integer')') | 'INTEGER');

integer : Digit+;

WS : [\t\n\r]+ -> skip;

ErrorChar: . ;
// * means 0 or more, + means 1 or more, ? means 0 or 1. Works with predefined objects or numbers or whatever 