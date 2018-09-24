grammar Parser;

query : relation_name '<-' expr ';';

relation_name : identifier;

identifier : Alpha *(Alpha | Digit)*;

Alpha : [a-z A-Z_]+;

Digit : [0-9]+;

expr : (atomic_expression | selection | projection | renaming | union | difference | product | natural_join);

atomic_expression : (relation_name | expr);

selection : 'select' ('('condition')' | condition) atomic_expression;

condition : conjunction ('||' conjunction)*;

conjunction : comparison ('&&' comparison)*;

comparison : operand op operand | ('('condition')' | condition);

op : ('==' | '!=' | '<' | '>' | '<=' | '=>');

operand : attribute_name | literal;

attribute_name : identifier;

literal : Alpha+ | Digit+;

projection : 'project' '('attribute_list')' atomic_expression;

attribute_list : attribute_name (',' attribute_name)*;

renaming : 'rename' '('attribute_list')' atomic_expression;

union : atomic_expression '+' atomic_expression;

difference : atomic_expression '-' atomic_expression;

product : atomic_expression '*' atomic_expression;

natural_join : atomic_expression '&' atomic_expression;

command : (open_cmd | close_cmd | write_cmd | exit_cmd | show_cmd | create_cmd | update_cmd | insert_cmd| delete_cmd);

open_cmd : 'open' relation_name;

close_cmd : 'close' relation_name;

write_cmd : 'write' relation_name;

exit_cmd : 'exit';

show_cmd : 'show' atomic_expression;

create_cmd : 'create table' relation_name '('typed_attribute_list')' 'primary key' '('attribute_list')';

update_cmd : ('update' relation_name 'set' attribute_name '=' literal (',' literal)*) | ('insert into' relation_name 'values from relation' expr);

insert_cmd : ('insert into' relation_name 'values from' (literal (','literal)*)) | ('insert into' relation_name 'values from relation' expr);  

delete_cmd : 'delete from' relation_name 'where' condition; 

typed_attribute_list : attribute_name type (',' attribute_name type)*;

type : ('varchar' '('integer')') | 'integer';

integer : Digit+;


// * means 0 or more, + means 1 or more, ? means 0 or 1. Works with predefined objects or numbers or whatever 