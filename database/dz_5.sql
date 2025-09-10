-- Запрос 1
select count(distinct t.id) as number_teachers
from teachers as t
join lectures as l on l.teacher_id = t.id
join groupslectures as gl on gl.lecture_id = l.id
join groups as g on gl.group_id = g.id
join departments as d on g.department_id = d.id
where d.name = 'Software Development';

-- Запрос 2
select count(l.id) as number_lectures
from lectures as l
join teachers as t on l.teacher_id = t.id
where t.surname = 'Johnson';

-- Запрос 3
select count(*) as number_subjects 
from subjects as s
join lectures as l on l.subject_id = s.id
where l.lectureroom = 'D404';

--Запрос 4
select l.lectureroom, count(*) as number_lectures
from lectures as l
group by lectureroom;

-- Запрос 5
select count(distinct g.id) as number_groups
from groups as g
join groupslectures as gl on gl.group_id = g.id
join lectures as l on gl.lecture_id = l.id
join teachers as t on l.teacher_id = t.id
where t.surname = 'Miller';

-- Запрос 6
select avg(distinct t.salary) from teachers as t
join lectures as l on l.teacher_id = t.id
join groupslectures as gl on gl.lecture_id = l.id
join groups as g on gl.group_id = g.id
join departments as d on g.department_id = d.id
join faculties as f on d.faculty_id = f.id
where f.name = 'Science';

-- запрос 8
select avg(d.financing) from departments as d;

-- Запрос 9
select t.name || ' ' || t.surname as full_name, count(l.subject_id) as number_lectures
from teachers as t
join lectures as l on l.teacher_id = t.id
group by t.name, t.surname;

-- Запрос 10
select gl.dayofweek, count(gl.lecture_id) as number_lectures
from groupslectures as gl
group by gl.dayofweek;

-- Запрос 11
select l.lectureroom, count(distinct d.id) 
from lectures as l
join groupslectures as gl on gl.lecture_id = l.id
join groups as g on gl.group_id = g.id
join departments as d on g.department_id = d.id
group by l.lectureroom;

-- Запрос 12 
select f.name, count(distinct s.id)
from faculties as f
join departments as d on d.faculty_id = f.id
join groups as g on g.department_id = d.id
join groupslectures as gl on gl.group_id = g.id
join lectures as l on gl.lecture_id = l.id
join subjects as s on l.subject_id = s.id
group by f.name;