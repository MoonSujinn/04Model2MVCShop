package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Test
	public void testAddPurchase() throws Exception {
		
		
		User user = new User();
		user.setUserId("user23");
		
		Product product = new Product();
		product.setProdNo(10082);
		
		Purchase purchase = new Purchase();
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("문자몽");
		purchase.setReceiverPhone("010-1111-1111");
		purchase.setDivyAddr("서울");
		purchase.setDivyRequest("없음");
		
		purchaseService.addPurchase(purchase);
		

		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertEquals("user23", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals("문자몽", purchase.getReceiverName());
		Assert.assertEquals("010-1111-1111", purchase.getReceiverPhone());
		Assert.assertEquals("서울", purchase.getDivyAddr());
		Assert.assertEquals("없음", purchase.getDivyRequest());
		Assert.assertEquals("21-03-23", purchase.getDivyDate());

	}

	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(10060);

		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("문자몽", purchase.getReceiverName());
		Assert.assertEquals("010-1111-1111", purchase.getReceiverPhone());
		Assert.assertEquals("서울", purchase.getDivyAddr());
		Assert.assertEquals("없음", purchase.getDivyRequest());

		Assert.assertNotNull(purchaseService.getPurchase(10060));
	}
	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10060);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("문자몽", purchase.getReceiverName());
		Assert.assertEquals("010-1111-1111", purchase.getReceiverPhone());
		Assert.assertEquals("서울", purchase.getDivyAddr());
		Assert.assertEquals("없음", purchase.getDivyRequest());

		purchase.setPaymentOption("2");
		purchase.setReceiverName("문몽자");
		purchase.setReceiverPhone("010-2222-2222");
		purchase.setDivyAddr("서울특별시");
		purchase.setDivyRequest("부재시 문앞");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10060);
		Assert.assertNotNull(purchase);
		
		//==> console 확인
		System.out.println(purchase);
			
		//==> API 확인
		Assert.assertEquals("2", purchase.getPaymentOption());
		Assert.assertEquals("문몽자", purchase.getReceiverName());
		Assert.assertEquals("010-2222-2222", purchase.getReceiverPhone());
		Assert.assertEquals("서울특별시", purchase.getDivyAddr());
		Assert.assertEquals("부재시 문앞", purchase.getDivyRequest());
	 }
	 
	 
	 
	 /*
	 //@Test
	 public void testGetPurchaseListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search,userId);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 }
	 
	 //@Test
	 public void testGetProductListByProdNo() throws Exception{
		 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("10060");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");

		 }
	 
	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("자몽사진");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 }	 
	 
	 //@Test
	 public void testGetProductListByPrice() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("3000");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	

	 }
	 
	 */
	 
}
	

	