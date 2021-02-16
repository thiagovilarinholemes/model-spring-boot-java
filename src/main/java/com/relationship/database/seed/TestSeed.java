package com.relationship.database.seed;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.relationship.entities.Authorization;
import com.relationship.entities.Category;
import com.relationship.entities.Permission;
import com.relationship.entities.Product;
import com.relationship.entities.Role;
import com.relationship.entities.SubCategory;
import com.relationship.entities.User;
import com.relationship.entities.enums.UserStatus;
import com.relationship.repositories.AuthorizationRepository;
import com.relationship.repositories.CategoryRepository;
import com.relationship.repositories.PermissionRepository;
import com.relationship.repositories.ProductRepository;
import com.relationship.repositories.RoleRepository;
import com.relationship.repositories.SubCategoryRepository;
import com.relationship.repositories.UserRepository;

@Configuration
public class TestSeed implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	private AuthorizationRepository authorizationRepository;	
//	@Autowired
//    private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		// Insert Categories
		Category c1 = new Category(null, "Eletrônico", "Produtos eletrônicos em geral.");
		Category c2 = new Category(null, "Móveis", "Móveis em geral.");
		Category c3 = new Category(null, "Cozinha", "Utensílios de cozinha.");
		Category c4 = new Category(null, "Vestuário", "Roupas masculinas e femininas.");
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		// Insert SubCategory
		SubCategory sc1 = new SubCategory(null, "Mobile", "Produtos eletrônicos em mobile - celular, notebooks.", c1);
		SubCategory sc2 = new SubCategory(null, "Celulares", "Celulares em geral - celular, smarthphone.", c1);
		SubCategory sc3 = new SubCategory(null, "Quarto", "Móveis para quarto em geral.", c2);
		SubCategory sc4 = new SubCategory(null, "Vasilhas", "Utensílios para cozinhar.", c3);
		SubCategory sc5 = new SubCategory(null, "Moda Masculino", "Roupas masculina.", c4);
		SubCategory sc6 = new SubCategory(null, "Moda Feminina", "Roupas feminina.", c4);
		subCategoryRepository.saveAll(Arrays.asList(sc1, sc2, sc3, sc4, sc5, sc6));
		
		// Insert Product
		Product p1 = new Product(null, "Celular LG K9", "Celular LG K9 última geração", 699.66, true, "celular lg");
		Product p2 = new Product(null, "Notebook Dell", "Notebook Dell i7 8GB Ram", 1592.67, false, "Notebook dell");
		Product p3 = new Product(null, "Armário 6 portas", "Armário 6 portas 4 gavetas", 456.66, true, "armário guarda-roupa");
		Product p4 = new Product(null, "Panela de pressão", "Panela de pressão 6L", 125.89, true, "panela pressão");
		Product p5 = new Product(null, "Camiseta Masculina", "Camiseta Masculina Nike", 125.66, true, "camisa camiseta");
		Product p6 = new Product(null, "Camiseta Feminina", "Camiseta Masculina Adidas", 699.66, true, "camisa camiseta");
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		// Insert Relationship tb_product_subcategory
		p1.getSubcategories().add(sc1);
		p1.getSubcategories().add(sc2);
		p2.getSubcategories().add(sc1);
		p3.getSubcategories().add(sc3);
		p4.getSubcategories().add(sc4);
		p5.getSubcategories().add(sc5);
		p6.getSubcategories().add(sc6);
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		// Insert Permissions
        Permission pe1 = new Permission(null, "view_users", "Permissão View Users");
        Permission pe2 = new Permission(null, "view_roles", "Permissão view Roles");
        Permission pe3 = new Permission(null, "view_home", "Permissão View Home");
        permissionRepository.saveAll(Arrays.asList(pe1, pe2, pe3));

        // Insert Roles
        Role r1 = new Role(null, "ADMIN", "Administrador do Sistema");
        Role r2 = new Role(null, "MANAGER", "Gerente do Sistema");
        Role r3 = new Role(null, "USER", "Usuário do Sistema");
        roleRepository.saveAll(Arrays.asList(r1, r2, r3));

        // Created association Role and Permission
        r1.getPermissions().add(pe1);
        r1.getPermissions().add(pe2);
        r2.getPermissions().add(pe1);
        r3.getPermissions().add(pe3);
        roleRepository.saveAll(Arrays.asList(r1, r2, r3));

        // Insert User
        User u1 = new User(null, "Thiago V.", "thiago.lemes", "123", UserStatus.ACTIVE, r1);
//        User u2 = new User(null, "Carina V.", "carina.lima", passwordEncoder.encode("123"), UserStatus.BLOCKED, r2);
//        User u3 = new User(null, "Spack Rella", "spack.rella", passwordEncoder.encode("123"), UserStatus.SUSPENDED, r3);
        userRepository.saveAll(Arrays.asList(u1));

        // Insert Authorization
        Authorization a1 = new Authorization(null, "edit_users", "Autorização Edite Users");
        Authorization a2 = new Authorization(null, "edit_roles", "Autorização Edite Roles");
        Authorization a3 = new Authorization(null, "create_user", "Autorização Create User");
        Authorization a4 = new Authorization(null, "view_roles", "Autorização View Roles");
        authorizationRepository.saveAll(Arrays.asList(a1, a2, a3, a4));

        // Created association Authorization User
        a1.getUsers().add(u1);
        a2.getUsers().add(u1);
        a3.getUsers().add(u1);
//        a4.getUsers().add(u2);
//        a4.getUsers().add(u3);
        authorizationRepository.saveAll(Arrays.asList(a1, a2, a3));
        
		
	}

}
