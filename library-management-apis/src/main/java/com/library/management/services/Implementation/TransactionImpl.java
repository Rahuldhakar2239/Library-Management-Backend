package com.library.management.services.Implementation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entities.Transaction;
import com.library.management.entities.User;
import com.library.management.repositories.TransactionRepo;
import com.library.management.services.TransactionService;

@Service
public class TransactionImpl implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;

	Calendar c = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@Override
	public void createTransaction(User user, Integer amount) {
		Transaction t = new Transaction();
		t.setAmount(amount);
		String today = sdf.format(c.getTime());
		t.setTime(today);
		t.setUser_name(user.getName());
		t.setUser_id(user.getId());
		this.transactionRepo.save(t);
	}

	@Override
	public List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

}
