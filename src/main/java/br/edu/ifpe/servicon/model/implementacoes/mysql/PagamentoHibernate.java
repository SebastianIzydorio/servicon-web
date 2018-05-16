/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.servicon.model.implementacoes.mysql;

import br.edu.ifpe.servicon.model.entidades.Pagamento;
import br.edu.ifpe.servicon.model.interfaces.PagamentoInterfaceDAO;
import br.edu.ifpe.servicon.model.utill.HibernateUtill;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Your Sebastião Izidorio <sebastian.izydorio@gmail.com>
 */
public class PagamentoHibernate implements PagamentoInterfaceDAO{
    
    private static PagamentoHibernate instance = null;
    private final HibernateUtill utill;
    private Session session;
    
    public static PagamentoHibernate getInstance() {
        if (instance == null) {
            instance = new PagamentoHibernate();
        }
        return instance;
    }
    
    public PagamentoHibernate() {
        this.utill = HibernateUtill.getInstance();
    }

    @Override
    public void criar(Pagamento pagamento) {
        session = utill.getSession();
        Transaction t = session.beginTransaction();
        try {
            session.persist(pagamento);
            t.commit();
        } catch (Exception addPagamentoException) {
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Pagamento recuperar(int codigo) {
        session = utill.getSession();
        try {
            return (Pagamento) session.createQuery
            ("FROM Pagamento WHERE id_pagamento = " + codigo).getResultList().get(0);
        } catch (Exception recPagamentoException) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(Pagamento pagamento) {
        session = utill.getSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(pagamento);
            t.commit();
        } catch (Exception updatePagamentoException) {
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(Pagamento pagamento) {
        session = utill.getSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(pagamento);
            t.commit();
        } catch (Exception delPagamentoException) {
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Pagamento> recuperarTodos() {
        session = utill.getSession();
        List<Pagamento> pagamentos = null;
        try {
            pagamentos = (List) session.createQuery
            ("FROM Pagamento").getResultList();
        } catch (Exception recTodosPagamentoException) {
            return null;
        } finally {
            session.close();
            return pagamentos;
        }
    }
    
}
