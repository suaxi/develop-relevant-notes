import { useDispatch, useSelector } from 'react-redux'
import { increment, decrement, addByNum } from './store/modules/counterStore'
import { getChannelList } from './store/modules/channelStore'
import { useEffect } from 'react'

function App() {
  const { count } = useSelector((state) => state.counter)
  const { channelList } = useSelector((state) => state.channel)
  const dispatch = useDispatch()
  useEffect(() => {
    dispatch(getChannelList())
  }, [dispatch])

  return (
    <div className="App">
      <button onClick={() => dispatch(decrement())}>-</button>
      {count}
      <button onClick={() => dispatch(increment())}>+</button>
      <button onClick={() => dispatch(addByNum(10))}>+ 10</button>
      <button onClick={() => dispatch(addByNum(20))}>+ 20</button>
      <ul>
        {channelList.map((item) => (
          <li key={item.id}>{item.name}</li>
        ))}
      </ul>
    </div>
  )
}

export default App
