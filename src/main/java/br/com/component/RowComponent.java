package br.com.component;

import java.io.IOException;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent("br.com.component.RowComponent")
public class RowComponent extends UINamingContainer {

	private String columns;

	@Override
	public void encodeEnd(final FacesContext context) throws IOException {
		final List<UIComponent> children = getChildren();
		final String[] cols = columns.split(",");

		if (!children.isEmpty()) {
			for (int i = 0; i < cols.length; i++) {
				if (children.get(i).isRendered()) {
					setChildrens(context, children.get(i), cols[i]);
				}

			}
		}

		super.encodeEnd(context);
	}

	private void setChildrens(final FacesContext context, final UIComponent children, final String cols)
			throws IOException {
		final ResponseWriter writerChild = context.getResponseWriter();
		writerChild.startElement("div", this);
		writerChild.writeAttribute("class", cols, null);
		children.encodeAll(context);
		writerChild.endElement("div");
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(final String columns) {
		this.columns = columns;
	}
}
