/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Winona Wixson 
 * Created: Apr 26, 2016
 */

DROP DATABASE IF EXISTS ParksNRec_Test;

CREATE DATABASE ParksNRec_Test;

Use ParksNRec_Test;

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
	blogPostId int(11) NOT NULL ,
	categoryId int(11) NOT NULL,
	FOREIGN KEY(blogPostId) REFERENCES BlogPosts(id) ON DELETE CASCADE,
	FOREIGN KEY(categoryId) REFERENCES Categories(id) ON DELETE CASCADE
);

