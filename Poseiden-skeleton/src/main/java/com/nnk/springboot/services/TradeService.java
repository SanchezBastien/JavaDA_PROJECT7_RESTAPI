package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Optional<Trade> getTradeById(Integer id) {
        return tradeRepository.findById(id);
    }

    public void deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
    }
}