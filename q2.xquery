 
 create or replace view review as select rx.*
    from REVIEWTABLE rt,
    XMLTABLE('for $x in /Review where year-from-dateTime($x/Review_Date)=2014  return $x'
    
             PASSING rt.REVIEWROW
             COLUMNS title VARCHAR2(30) PATH 'Book_Title'
             ) rx;

create or replace view book as select bx.*
    from BOOKTABLE bt,
    XMLTABLE('for $y in /Book return $y'
             PASSING bt.BOOKROW
             COLUMNS title VARCHAR2(30) PATH 'Title',
              b_id VARCHAR2(10) PATH '@ID'
              ) bx;

select Distinct book.b_id as bookID
from  review, book
 where review.title=book.title; 
 drop view review;
 drop view book;
 
  
  
  
  