 select rx.*
    from REVIEWTABLE rt,
    XMLTABLE('for $x in /Review return $x'
             PASSING rt.REVIEWROW
             COLUMNS reviewer VARCHAR2(30) PATH 'Reviewer', 
             title VARCHAR2(30) PATH 'Book_Title'
             
             )  rx order by rx.reviewer asc,rx.title asc ;
