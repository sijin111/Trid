package product.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import common.domain.PagingDTO;

import product.domain.CategoryDTO;
import product.domain.ProductDTO;

public interface ProductDAO {
	
	// 관리자 상품 등록
	int insertProduct(ProductDTO productDTO) throws SQLException;
	
	// 카테고리 전체 목록 조회
	List<CategoryDTO> selectCategoryList() throws SQLException;
	
	// 상품 리스트 전체 행 개수 조회
	int selectTotalRowCount(Map<String, Object> paraMap) throws SQLException ;

	// 관리자 상품 리스트 조회
	List<ProductDTO> selectProductList(Map<String, Object> paraMap) throws SQLException;

	// 관리자 상품 상세 조회
	ProductDTO selectProduct(String productNo) throws SQLException;

	// 관리자 상품 삭제
	int deleteProduct(String productNo) throws SQLException;

	// 관리자 상품 수정
	int updateProduct(ProductDTO productDTO) throws SQLException;

}
