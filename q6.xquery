 
 create or replace view review as select rx.*
    from REVIEWTABLE rt,
    XMLTABLE('for $x in /Review where $x/Rating>3 return $x'
             PASSING rt.REVIEWROW
             COLUMNS title VARCHAR2(30) PATH 'Book_Title',
             rating VARCHAR2(30) PATH 'Rating',
             reviewer VARCHAR2(30) PATH 'Reviewer',
              review_date VARCHAR2(30) PATH 'Review_Date'
             ) rx;

create or replace view book as select bx.*
    from BOOKTABLE bt,
    XMLTABLE('for $x in /Book return $x'
             PASSING bt.BOOKROW
             COLUMNS title VARCHAR2(30) PATH 'Title',
              publish_date VARCHAR2(30) PATH 'Publish_Date'
              ) bx;

select  review.reviewer as reviewer, review.rating as rating,review.review_date as reviewDate, book.publish_date as publishDate
from  review, book
 where review.title=book.title
 order by  book.publish_date asc,review.review_date desc;
 drop view review;
 drop view book;
   
  
  
  
  