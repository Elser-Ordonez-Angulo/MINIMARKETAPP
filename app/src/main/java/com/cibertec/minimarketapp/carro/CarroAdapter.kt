
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.minimarketapp.carro.Carro
import com.cibertec.minimarketapp.carro.CarroViewHolder


class CarroAdapter(val listener: CarroAdapter.CarroListener):
    RecyclerView.Adapter<CarroViewHolder>(), CarroViewHolder.CategoriaHolderListener {


    var lisCarro = emptyList<Carro>()

    fun loadData(productos: List<Carro>) {
        this.lisCarro = productos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CarroViewHolder(inflater, parent, this)
    }

    override fun getItemCount(): Int {
        return lisCarro.size
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        val producto = lisCarro[position]
        holder.bind(producto)
    }

    interface CarroListener {
        fun getCarroSelected(carro: Carro)
        fun removeCarroSelected(carro: Carro)
    }

    override fun getCategoriaRemoveHolderSelected(position: Int) {
        val carro = lisCarro[position]
        Log.v("REMOVE_CARRO", "3")
        listener.removeCarroSelected(carro)
    }

}