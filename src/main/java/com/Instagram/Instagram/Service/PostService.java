package com.Instagram.Instagram.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Instagram.Instagram.Entity.Post;
import com.Instagram.Instagram.Entity.User;
import com.Instagram.Instagram.Repository.PostRepository;
import com.Instagram.Instagram.Repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	public Post createPost(Post post, MultipartFile imageFile, Long userId) throws IOException {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//		Post post1 = new Post();
		post.setUser(user);

		post.setImagePath(getFileName(imageFile));
		return postRepository.save(post);
	}

	private String getFileName(MultipartFile file) throws IOException {
		//InputStream input = file.getInputStream();
		return file.getOriginalFilename();

	}
}
