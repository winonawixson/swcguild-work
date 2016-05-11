DROP DATABASE IF EXISTS ParksNRec;

CREATE DATABASE ParksNRec;

Use ParksNRec;

CREATE TABLE BlogPosts (
	id int(11) NOT NULL AUTO_INCREMENT,
	title varchar(50) NOT NULL,
	author varchar(100),
	postContent BLOB,
	currentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	approved tinyint(1),
	PRIMARY KEY (id)	
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE Tags (
	id int(11) NOT NULL AUTO_INCREMENT,
	tagName varchar(50) NOT NULL,
	PRIMARY KEY (id)	
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE Categories (
	id int(11) NOT NULL AUTO_INCREMENT,
	categoryName varchar(50) NOT NULL,
	PRIMARY KEY (id)	
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE BlogPostTags (
	blogPostId int(11) NOT NULL ,
	tagId int NOT NULL,
	FOREIGN KEY(blogPostId) REFERENCES BlogPosts(id) ON DELETE CASCADE,
	FOREIGN KEY(tagId) REFERENCES Tags(id) ON DELETE CASCADE
);

CREATE TABLE BlogPostCategories (
	blogPostId int(11) NOT NULL UNIQUE,
	categoryId int(11) NOT NULL,
	FOREIGN KEY(blogPostId) REFERENCES BlogPosts(id) ON DELETE CASCADE,
	FOREIGN KEY(categoryId) REFERENCES Categories(id) ON DELETE CASCADE
);


INSERT INTO BlogPosts (title, author, postContent, approved)
VALUES ('The Story of the Malakand Field Force', 'Winston S. Churchill', 'But the Mahommedan religion increases, instead of lessening, 
the fury of intolerance. It was originally propagated by the sword, and ever since, its 
votaries have been subject, above the people of all other creeds, to this form of madness. 
In a moment the fruits of patient toil, the prospects of material prosperity, the fear of death 
itself, are flung aside. The more emotional Pathans are powerless to resist. All rational 
considerations are forgotten. Seizing their weapons, they become Ghazis—as dangerous and as 
sensible as mad dogs: fit only to be treated as such. While the more generous spirits among the
 tribesmen become convulsed in an ecstasy of religious bloodthirstiness, poorer and more material 
souls derive additional impulses from the influence of others, the hopes of plunder and the joy of
 fighting. Thus whole nations are roused to arms. Thus the Turks repel their enemies, the Arabs of
 the Soudan break the British squares, and the rising on the Indian frontier spreads far and wide. 
In each case civilisation is confronted with militant Mahommedanism. The forces of progress clash
 with those of reaction. The religion of blood and war is face to face with that of peace.', 1);
INSERT INTO BlogPosts (title, author, postContent, approved)
VALUES ('Power of Progress', 'Nikola Tesla', 'The day science begins to study non-physical phenomena, it will make more progress in one 
decade than in all the previous centuries of its existence.', 1);
INSERT INTO BlogPosts (title, author, postContent, approved)
VALUES ('Computing Machinery and Intelligence', 'Alan Turing', 'We can only see a short distance ahead, but we can see 
plenty there that needs to be done.', 1);
INSERT INTO BlogPosts (title, author, postContent, approved)
VALUES ('What is Enlightenment?', 'Immanuel Kant', 'But to unite in a permanent religious 
institution which is not to be subject to doubt before the public even in the 
lifetime of one man, and thereby to make a period of time fruitless in the progress of mankind 
toward improvement, thus working to the disadvantage of posterity - that is absolutely forbidden.
 For himself (and only for a short time) a man may postpone enlightenment in what he ought to know,
 but to renounce it for posterity is to injure and trample on the rights of mankind.', 1);
INSERT INTO BlogPosts(title, author, postContent, approved)
VALUES ('Success', 'Henry Ford', 'Coming together is a beginning;
<br> keeping together is progress;<br> working together is success.<br> -Henry Ford', 0);


INSERT INTO Tags (tagName) VALUES ('#money');
INSERT INTO Tags (tagName) VALUES ('#mountains');
INSERT INTO Tags (tagName) VALUES ('#nature');

INSERT INTO Categories (categoryName) VALUES ('Campgrounds');
INSERT INTO Categories (categoryName) VALUES ('Hiking Trails');
INSERT INTO Categories (categoryName) VALUES ('Sunsets');

INSERT INTO BlogPostTags (blogPostId, tagId) VALUES (1, 1);
INSERT INTO BlogPostTags (blogPostId, tagId) VALUES (1, 2);
INSERT INTO BlogPostTags (blogPostId, tagId) VALUES (5, 3);
INSERT INTO BlogPostTags (blogPostId, tagId) VALUES (2, 2);
INSERT INTO BlogPostTags (blogPostId, tagId) VALUES (4, 3);
INSERT INTO BlogPostTags (blogPostId, tagId) VALUES (3, 3);

INSERT INTO BlogPostCategories (blogPostId, categoryId) VALUES (1, 1);
INSERT INTO BlogPostCategories (blogPostId, categoryId) VALUES (2, 3);
INSERT INTO BlogPostCategories (blogPostId, categoryId) VALUES (3, 3);
INSERT INTO BlogPostCategories (blogPostId, categoryId) VALUES (4, 2);
INSERT INTO BlogPostCategories (blogPostId, categoryId) VALUES (5, 3);
	
--
-- Table structure for table `users`
--
CREATE TABLE IF NOT EXISTS `users` (
 `user_id` int(11) NOT NULL AUTO_INCREMENT,
 `username` varchar(20) NOT NULL,
 `password` varchar(20) NOT NULL,
 `enabled` tinyint(1) NOT NULL,
 PRIMARY KEY (`user_id`),
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
--
-- Dumping data for table `users`
--
INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`) VALUES
(1, 'test', 'password', 1),
(2, 'test2', 'password', 1);
--
-- Table structure for table `authorities`
--
CREATE TABLE IF NOT EXISTS `authorities` (
 `username` varchar(20) NOT NULL,
 `authority` varchar(20) NOT NULL,
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`username`, `authority`) VALUES
('test', 'ROLE_ADMIN'),
('test', 'ROLE_PERSON'),
('test2', 'ROLE_PERSON');
--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);











