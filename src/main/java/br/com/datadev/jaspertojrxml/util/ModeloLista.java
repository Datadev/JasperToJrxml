package br.com.datadev.jaspertojrxml.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Fabrício
 */
public class ModeloLista extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private final static int FIRSTINDEX = 0;

    private final List<Object> elementos;
    private Object itemSelecionado;

    public ModeloLista(List<Object> elementos) {
        this();
        this.elementos.addAll(elementos);
        if (this.elementos.size() > 0) {
            this.itemSelecionado = this.elementos.get(FIRSTINDEX);
        }
    }

    public ModeloLista() {
        this.elementos = new ArrayList<>();
    }

    @Override
    public void setSelectedItem(Object item) {
        this.itemSelecionado = item;
    }

    @Override
    public Object getSelectedItem() {
        return this.itemSelecionado;
    }

    @Override
    public int getSize() {
        return elementos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return elementos.get(index);
    }
    
    public List<Object> getAll(){
        return this.elementos;
    }
            

    public void addElement(Object item) {
        this.elementos.add(item);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
        setSelectedItem(elementos.get(getSize() - 1));
    }

    public void addElements(List<Object> elementos) {
        int primeiraLinha = getSize();
        this.elementos.addAll(elementos);
        fireIntervalAdded(this, primeiraLinha, primeiraLinha + elementos.size());
        setSelectedItem(this.elementos.get(getSize() - 1));
    }

    public void removeElement() {
        this.elementos.remove(getSelectedItem());
        if (getSize() > 0) {
            fireIntervalRemoved(this, FIRSTINDEX, getSize() - 1);
            setSelectedItem(this.elementos.get(FIRSTINDEX));
        } else {
            fireIntervalRemoved(this, FIRSTINDEX, getSize());
        }
    }

    public void removeElement(int index) {
        this.elementos.remove(index);
        if (getSize() > 0) {
            fireIntervalRemoved(this, FIRSTINDEX, getSize() - 1);
            setSelectedItem(this.elementos.get(FIRSTINDEX));
        } else {
            fireIntervalRemoved(this, FIRSTINDEX, getSize());
        }
    }

    public void clear() {
        this.elementos.clear();
        fireContentsChanged(this, FIRSTINDEX, getSize() - 1);
    }
}
